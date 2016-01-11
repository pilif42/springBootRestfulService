package com.example.springboot.steps;

import com.example.springboot.util.World;
import cucumber.api.java.en.When;

public class CustomerSteps {

    private final ResponseAware responseAware;
    private final World world;

    public CustomerSteps(World world, ResponseAware responseAware) {
        this.responseAware = responseAware;
        this.world = world;
    }

    @When("^I make the GET call to the customer controller with id ([^\"]*)$")
    public void i_make_the_GET_call_to_the_customer_controller_with_id(int id) throws Throwable {
        responseAware.invokeCustomerEndpointWithId(id);
    }

    @When("^I make the REST call to the POST customer endpoint$")
    public void i_make_the_REST_call_to_the_post_customer_endpoint() throws Throwable {
        responseAware.invokePostCustomerEndpoint();
    }
}
