package example.stepdefinitions;

import example.steps.NewIncomeSteps;
import io.cucumber.java8.En;

/**
 * Created by sekarayukarindra.
 */
public class NewIncomeStepDefinitions implements En {

    public NewIncomeStepDefinitions() {
        When("user input new income", NewIncomeSteps::inputNewIncome);
    }
}
