package com.example.springboot.steps;

import com.example.springboot.util.World;
import cucumber.api.java.en.When;

public class HealthcheckSteps {

    private final ResponseAware responseAware;
    private final World world;

    public HealthcheckSteps(World world, ResponseAware responseAware) {
        this.responseAware = responseAware;
        this.world = world;
    }

    @When("^I make the GET call to the default healthcheck$")
    public void i_make_the_GET_call_to_the_default_healthcheck() throws Throwable {
        responseAware.invokeDefaultHealthcheckEndpoint();
    }
}
