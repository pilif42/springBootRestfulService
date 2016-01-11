package com.example.springboot.steps;

import com.example.springboot.util.World;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class GreetingSteps {

    private final ResponseAware responseAware;
    private final World world;

    public GreetingSteps(World world, ResponseAware responseAware) {
        this.responseAware = responseAware;
        this.world = world;
    }

    @When("^I make the GET call to the greeting controller with name \"([^\"]*)\"$")
    public void i_make_the_GET_call_to_the_greeting_controller_with_name(String name) throws Throwable {
        responseAware.invokeGreetingEndpointWithName(name);
    }

    @When("^I make the GET call to the customer controller with id ([^\"]*)$")
    public void i_make_the_GET_call_to_the_customer_controller_with_id(int id) throws Throwable {
        responseAware.invokeCustomerEndpointWithId(id);
    }

    @Given("^an invalid JSON Google receipt$")
    public void an_invalid_json_Google_receipt() throws Throwable {
        responseAware.setGoogleReceipt(world.getProperty("cuc.invalid.sub.google.receipt"));
    }

    @Given("^a valid JSON Google receipt$")
    public void a_valid_json_Google_receipt() throws Throwable {
        responseAware.setGoogleReceipt(world.getProperty("cuc.valid.sub.google.receipt"));
    }

    @When("^I post the receipt to the Greeting endpoint$")
    public void I_post_the_receipt_to_the_Greeting_endpoint() throws Throwable {
        responseAware.postToGreetingEndpoint();
    }
}
