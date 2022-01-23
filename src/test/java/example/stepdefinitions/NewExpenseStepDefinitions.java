package example.stepdefinitions;

import example.steps.NewExpenseSteps;
import io.cucumber.java8.En;

/**
 * Created by sekarayukarindra.
 */
public class NewExpenseStepDefinitions implements En {

    public NewExpenseStepDefinitions() {
        When("user input new expense", NewExpenseSteps::inputNewExpense);
    }
}
