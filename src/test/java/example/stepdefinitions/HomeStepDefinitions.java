package example.stepdefinitions;

import example.steps.HomeSteps;
import io.cucumber.java8.En;

/**
 * Created by sekarayukarindra.
 */
public class HomeStepDefinitions implements En {

    public HomeStepDefinitions(){
        Given("user click income menu", HomeSteps::clickIncomeMenu);

        When("user verify new income", HomeSteps::verifyIncomeAdded);

        Given("user click expense menu", HomeSteps::clickExpenseMenu);

        When("user verify new expense", HomeSteps::verifyExpenseAdded);

        When("user verify updated balance", HomeSteps::verifyUpdatedBalanceAfterExpenseAdded);
    }
}
