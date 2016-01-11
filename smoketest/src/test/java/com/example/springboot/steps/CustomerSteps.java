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

    @When("^I make the REST call to the POST customer endpoint$")
    public void i_make_the_REST_call_to_the_post_customer_endpoint() throws Throwable {
        responseAware.invokePostCustomerEndpoint();
    }
}
