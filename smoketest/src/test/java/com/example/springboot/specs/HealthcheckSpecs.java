package com.example.springboot.specs;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:com/example/springboot/healthcheck.feature"},
        glue = {"com.example.springboot.steps"},
        format = {"pretty", "html:build/cucumber-html-report"},
        tags = {"~@ignore"}
)
public class HealthcheckSpecs {
}
