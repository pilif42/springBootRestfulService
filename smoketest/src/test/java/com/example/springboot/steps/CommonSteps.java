package com.example.springboot.steps;

import com.example.springboot.util.World;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommonSteps {

    private static final String BASIC_AUTH_USERNAME_PROPERTY_KEY = "cuc.basic.auth.username";
    private static final String BASIC_AUTH_PASSWORD_PROPERTY_KEY = "cuc.basic.auth.password";
    private static final String VALID_X_AN_ID_PROPERTY_KEY = "cuc.valid.xanid";

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

    @Then("^the response should contain the field \"([^\"]*)\" with value matching the regex \"([^\"]*)\"")
    public void the_response_should_contain_the_field_with_value_matching_pattern(String field, String regex) throws Throwable {
        String jsonString = JsonPath.read(responseAware.getBody(), "$." + field);
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        assertTrue(pattern.matcher(jsonString).matches());
    }

    @Then("^the response should contain the field \"([^\"]*)\" with an integer value$")
    public void the_response_should_contain_the_field_with_integer_value(String field) throws Throwable {
        Object obj = JsonPath.read(responseAware.getBody(), "$." + field);
        assertTrue(obj instanceof Integer);
    }

    @Then("^the response should contain the field \"([^\"]*)\" with an integer value of ([^\"]*)$")
    public void the_response_should_contain_the_field_with_integer_value_of(String field, int value) throws Throwable {
        Object obj = JsonPath.read(responseAware.getBody(), "$." + field);
        assertTrue(obj instanceof Integer);

        Integer retrievedValue = (Integer)obj;
        assertEquals(value, retrievedValue.intValue());
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

    @Given("^valid basic authentication credentials are provided$")
    public void valid_basic_authentication_credentials_are_provided() throws Throwable {
        responseAware.enableBasicAuth(world.getProperty(BASIC_AUTH_USERNAME_PROPERTY_KEY), world.getProperty(BASIC_AUTH_PASSWORD_PROPERTY_KEY));
    }

    @Given("^the JSON parameter \"(.*?)\" with value \"(.*?)\"$")
    public void the_JSON_parameter_with_value(String arg1, String arg2) throws Throwable {
        responseAware.addRequestProperty(arg1, arg2);
    }

    @Given("^A valid x-an-id$")
    public void a_valid_x_an_id() throws Throwable {
        responseAware.setXAndId(world.getProperty(VALID_X_AN_ID_PROPERTY_KEY));
    }
}
