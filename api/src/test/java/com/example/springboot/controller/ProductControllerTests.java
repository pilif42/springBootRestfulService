package com.example.springboot.controller;

import com.example.springboot.domain.Product;
import com.example.springboot.service.ProductService;
import com.example.springboot.utility.TestConstants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.isNull;
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
        // TODO jsonPath("$.startDate");
        // TODO jsonPath("$.endDate");
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
        // TODO jsonPath("$.mapCode");
    }
}
