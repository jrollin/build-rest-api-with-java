package com.talanlabs.training.bdd.steps;

import com.talanlabs.training.TrainingApplication;
import io.cucumber.java.en.Given;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TrainingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserStepDefinitionTest extends AbstractStepTest {

    @Given("an api user")
    public void asAnApiUser() {
    }
}
