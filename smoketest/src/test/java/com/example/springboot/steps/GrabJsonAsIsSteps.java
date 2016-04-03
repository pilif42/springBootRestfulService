package com.example.springboot.steps;

import com.example.springboot.util.World;
import cucumber.api.java.en.When;

public class GrabJsonAsIsSteps {

  private final ResponseAware responseAware;
  private final World world;

  public GrabJsonAsIsSteps(World world, ResponseAware responseAware) {
    this.responseAware = responseAware;
    this.world = world;
  }

  @When("^I make the GET call to the grabJsonAsIs controller$")
  public void i_make_the_GET_call_to_the_grabjsonasis_controller() throws Throwable {
    responseAware.invokeGrabJsonAsIsEndpointWithId();
  }

  @When("^I make the REST call to the POST grabJsonAsIs endpoint$")
  public void i_make_the_REST_call_to_the_post_grabJsonAsIs_endpoint() throws Throwable {
    responseAware.invokePostGrabJsonAsIsEndpoint();
  }

}
