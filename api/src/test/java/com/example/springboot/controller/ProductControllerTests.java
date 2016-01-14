package com.example.springboot.controller;

import com.example.springboot.domain.NonSubscriptionProduct;
import com.example.springboot.domain.Product;
import com.example.springboot.domain.SubscriptionProduct;
import com.example.springboot.error.OurException;
import com.example.springboot.service.ProductService;
import com.example.springboot.utility.TestConstants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.example.springboot.utility.JsonObjectMapperUtils.jsonMapper;
import static com.example.springboot.utility.MvcHelper.postJson;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.example.springboot.utility.MockMvcControllerAdviceHelper.mockAdviceFor;
import static com.example.springboot.utility.MvcHelper.getJson;
import static com.example.springboot.utility.ProductBuilder.product;

public class ProductControllerTests {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
            .standaloneSetup(productController)
            .setHandlerExceptionResolvers(mockAdviceFor(RestExceptionHandler.class))
            .build();
    }

    @Test
    public void findNonSubscriptionProduct() throws Exception {
        Product ourMockNonSubscriptionProduct = product().id(TestConstants.NON_SUB_PRODUCT_ID_1).buildNonSubscriptionProduct();
        when(productService.findById(TestConstants.NON_SUB_PRODUCT_ID_1)).thenReturn(ourMockNonSubscriptionProduct);

        ResultActions actions = mockMvc.perform(getJson("/product").param("id", TestConstants.NON_SUB_PRODUCT_ID_1.toString()));

        actions.andExpect(status().isOk());
        actions.andExpect(handler().handlerType(ProductController.class));
        actions.andExpect(handler().methodName("findProduct"));
        actions.andExpect(jsonPath("$.id", is(TestConstants.NON_SUB_PRODUCT_ID_1)));
        actions.andExpect(jsonPath("$.name", is(TestConstants.NON_SUB_PRODUCT_NAME_1)));
        actions.andExpect(jsonPath("$.mapCode", is(TestConstants.NON_SUB_PRODUCT_MAP_CODE_1)));
        actions.andExpect(jsonPath("$.startDate").doesNotExist());
        actions.andExpect(jsonPath("$.endDate").doesNotExist());
    }

    @Test
    public void findSubscriptionProduct() throws Exception {
        Product ourMockSubscriptionProduct = product().id(TestConstants.SUB_PRODUCT_ID_1).buildSubscriptionProduct();
        when(productService.findById(TestConstants.SUB_PRODUCT_ID_1)).thenReturn(ourMockSubscriptionProduct);

        ResultActions actions = mockMvc.perform(getJson("/product").param("id", TestConstants.SUB_PRODUCT_ID_1.toString()));

        actions.andExpect(status().isOk());
        actions.andExpect(handler().handlerType(ProductController.class));
        actions.andExpect(handler().methodName("findProduct"));
        actions.andExpect(jsonPath("$.id", is(TestConstants.SUB_PRODUCT_ID_1)));
        actions.andExpect(jsonPath("$.name", is(TestConstants.SUB_PRODUCT_NAME_1)));
        actions.andExpect(jsonPath("$.startDate", is(TestConstants.SUB_PRODUCT_START_DATE_1_INT)));
        actions.andExpect(jsonPath("$.endDate", is(TestConstants.SUB_PRODUCT_END_DATE_1_INT)));
        actions.andExpect(jsonPath("$.mapCode").doesNotExist());
    }

    @Test
    public void findProductNotFound() throws Exception {
        when(productService.findById(TestConstants.SUB_PRODUCT_ID_1)).thenReturn(null);

        ResultActions actions = mockMvc.perform(getJson("/product").param("id", TestConstants.SUB_PRODUCT_ID_1.toString()));

        actions.andExpect(status().isNotFound());
        actions.andExpect(handler().handlerType(ProductController.class));
        actions.andExpect(handler().methodName("findProduct"));
        actions.andExpect(jsonPath("$.error.code", is("UA-GP-102")));
        actions.andExpect(jsonPath("$.error.timestamp", isA(Long.class)));
        actions.andExpect(jsonPath("$.error.message", is("Product details not found")));
    }

    @Test
    public void storeSubscriptionProductBadRequest() throws Exception {
        String jsonRequestMissingStartDate = "{\"version\":1,\"created\":1452438934179,\"modified\":1452438934179,\"name\":\"SUB_2\",\"createdBy\":\"7c13f7e0-0c6d-7c4b-6327-ffde84545db0\",\"modifiedBy\":\"7c13f7e0-0c6d-7c4b-6327-ffde84545db0\",\"endDate\":1452438939999}";
        ResultActions actions = mockMvc.perform(
            postJson("/product/create/subscription", jsonRequestMissingStartDate)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        actions.andExpect(status().is4xxClientError());
        actions.andExpect(jsonPath("$.error.code", is("OS-102")));
        actions.andExpect(jsonPath("$.error.timestamp", isA(Long.class)));
        actions.andExpect(jsonPath("$.error.message", is("Invalid Request")));
    }

    @Test
     public void storeSubscriptionProductThrowsException() throws Exception {
        SubscriptionProduct subscriptionProduct = product().id(TestConstants.SUB_PRODUCT_ID_1).buildSubscriptionProduct();

        when(productService.saveSubscriptionProduct(subscriptionProduct)).thenThrow(new OurException(OurException.Fault.GENERIC_SERVER_SIDE_ERROR));

        String jsonRequest = jsonMapper.writeValueAsString(subscriptionProduct);
        ResultActions actions = mockMvc.perform(
            postJson("/product/create/subscription", jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        actions.andExpect(status().is5xxServerError());
        actions.andExpect(jsonPath("$.error.code", is("OS-016")));
        actions.andExpect(jsonPath("$.error.timestamp", isA(Long.class)));
        actions.andExpect(jsonPath("$.error.message", is("Request could not be completed because there is a problem with the service")));
    }

    @Test
    public void storeSubscriptionProductPositivePath() throws Exception {
        SubscriptionProduct subscriptionProduct = product().id(TestConstants.SUB_PRODUCT_ID_1).buildSubscriptionProduct();

        when(productService.saveSubscriptionProduct(subscriptionProduct)).thenReturn(1);

        String jsonRequest = jsonMapper.writeValueAsString(subscriptionProduct);
        ResultActions actions = mockMvc.perform(
            postJson("/product/create/subscription", jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        actions.andExpect(status().is2xxSuccessful());
    }

    @Test
    public void storeNonSubscriptionProductBadRequest() throws Exception {
        String jsonRequestMissingMapCode = "{\"version\":1,\"created\":1452438934179,\"modified\":1452438934179,\"name\":\"NON_SUB_2\",\"createdBy\":\"7c13f7e0-0c6d-7c4b-6327-ffde84545db0\",\"modifiedBy\":\"7c13f7e0-0c6d-7c4b-6327-ffde84545db0\"}";
        ResultActions actions = mockMvc.perform(
            postJson("/product/create/subscription", jsonRequestMissingMapCode)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        actions.andExpect(status().is4xxClientError());
        actions.andExpect(jsonPath("$.error.code", is("OS-102")));
        actions.andExpect(jsonPath("$.error.timestamp", isA(Long.class)));
        actions.andExpect(jsonPath("$.error.message", is("Invalid Request")));
    }

    @Test
    public void storeNonSubscriptionProductThrowsException() throws Exception {
        NonSubscriptionProduct nonSubscriptionProduct = product().id(TestConstants.NON_SUB_PRODUCT_ID_1).buildNonSubscriptionProduct();

        when(productService.saveNonSubscriptionProduct(nonSubscriptionProduct)).thenThrow(new OurException(OurException.Fault.GENERIC_SERVER_SIDE_ERROR));

        String jsonRequest = jsonMapper.writeValueAsString(nonSubscriptionProduct);
        ResultActions actions = mockMvc.perform(
            postJson("/product/create/nonsubscription", jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        actions.andExpect(status().is5xxServerError());
        actions.andExpect(jsonPath("$.error.code", is("OS-016")));
        actions.andExpect(jsonPath("$.error.timestamp", isA(Long.class)));
        actions.andExpect(jsonPath("$.error.message", is("Request could not be completed because there is a problem with the service")));
    }

    @Test
    public void storeNonSubscriptionProductPositivePath() throws Exception {
        NonSubscriptionProduct nonSubscriptionProduct = product().id(TestConstants.NON_SUB_PRODUCT_ID_1).buildNonSubscriptionProduct();

        when(productService.saveNonSubscriptionProduct(nonSubscriptionProduct)).thenReturn(1);

        String jsonRequest = jsonMapper.writeValueAsString(nonSubscriptionProduct);
        ResultActions actions = mockMvc.perform(
            postJson("/product/create/nonsubscription", jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        actions.andExpect(status().is2xxSuccessful());
    }
}
