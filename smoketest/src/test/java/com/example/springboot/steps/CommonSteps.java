package com.example.springboot.steps;

import com.example.springboot.util.World;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommonSteps {

    private final ResponseAware responseAware;
    private final World world;

    public CommonSteps(World world, ResponseAware responseAware) {
        this.responseAware = responseAware;
        this.world = world;
    }

    @Then("^the response status should be (\\d+)$")
    public void the_response_status_should_be(int arg1) throws Throwable {
        System.out.println("responseAware.getStatus() = " + responseAware.getStatus());
        assertEquals(arg1, responseAware.getStatus());
    }

    @Then("^the response should contain the field \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void the_response_should_contain_the_field_with_value(String field, String value) throws Throwable {
        assertEquals(value, JsonPath.read(responseAware.getBody(), "$." + field));
    }

    @Then("^the response should contain the field \"([^\"]*)\" with a long value$")
    public void the_response_should_contain_the_field_with_long_value(String field) throws Throwable {
        Object obj = JsonPath.read(responseAware.getBody(), "$." + field);
        assertTrue(obj instanceof Long);
    }

    @Then("^the response should contain the field \"([^\"]*)\" with a String value$")
    public void the_response_should_contain_the_field_with_string_value(String field) throws Throwable {
        Object obj = JsonPath.read(responseAware.getBody(), "$." + field);
        assertTrue(obj instanceof String);
    }

}
