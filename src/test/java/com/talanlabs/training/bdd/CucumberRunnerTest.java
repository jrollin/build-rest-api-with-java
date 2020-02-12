package com.talanlabs.training.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", plugin = {
        "html:target/cucumber-report"}, monochrome = true, glue = {"com.talanlabs.training.bdd.steps"})
public class CucumberRunnerTest {

}