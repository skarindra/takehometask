package example.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

/**
 * Created by sekarayukarindra.
 */
public class HomeSteps extends BaseSteps {

    private static Logger LOGGER = LogManager.getLogger(HomeSteps.class);

    private static String continueOnboarding = "com.monefy.app.lite:id/buttonContinue";
    private static String closeOnboarding = "com.monefy.app.lite:id/buttonClose";
    private static String incomeMenu = "com.monefy.app.lite:id/income_button_title";
    private static String expenseMenu = "com.monefy.app.lite:id/expense_button_title";
    private static String incomeAmount = "com.monefy.app.lite:id/income_amount_text";
    private static String expenseAmount = "com.monefy.app.lite:id/expense_amount_text";
    private static String balanceAmount = "com.monefy.app.lite:id/balance_amount";

    //pre-data
    private static String amountIncome = "1000";
    private static String amountExpense = "100";

    private static void checkOnboarding(){
        //Get started, amazing, I'm ready
        for(int i=0;i<3;i++){
            clickById(continueOnboarding);
        }
        clickById(closeOnboarding);
    }

    public static void clickIncomeMenu(){
        checkOnboarding();
        clickById(incomeMenu);
    }

    public static void clickExpenseMenu(){
        clickById(expenseMenu);
    }

    public static void verifyIncomeAdded(){
        String[] incomeVal = getText(incomeAmount).split("[\\.]");
        String rawIncome = incomeVal[0].replaceAll("[\\D]", "");

        LOGGER.info("income = "+incomeVal[0]);
        LOGGER.info("raw income = "+rawIncome);

        String[] value = getText(balanceAmount).split("[\\.]");
        String rawBalance = value[0].replaceAll("[\\D]", "");

        Assert.assertEquals(rawIncome, amountIncome);
        Assert.assertEquals(rawBalance, amountIncome);
    }

    public static void verifyExpenseAdded(){
        String[] expenseVal = getText(expenseAmount).split("[\\.]");
        String rawExpense = expenseVal[0].replaceAll("[\\D]", "");

        LOGGER.info("expense = "+expenseVal[0]);
        LOGGER.info("raw expense = "+rawExpense);

        Assert.assertEquals(rawExpense, amountExpense);

    }

    public static void verifyUpdatedBalanceAfterExpenseAdded(){
        String[] value = getText(balanceAmount).split("[\\.]");
        String rawBalance = value[0].replaceAll("[\\D]", "");

        Assert.assertEquals(rawBalance, String.valueOf(Integer.parseInt(amountIncome) - Integer.parseInt(amountExpense)));
    }
}
