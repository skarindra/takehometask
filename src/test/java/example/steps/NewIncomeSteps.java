package example.steps;

/**
 * Created by sekarayukarindra.
 */
public class NewIncomeSteps extends BaseSteps {

    private static String incomeKey1 = "com.monefy.app.lite:id/buttonKeyboard1";
    private static String incomeKey0 = "com.monefy.app.lite:id/buttonKeyboard0";
    private static String newIncomeNote = "com.monefy.app.lite:id/textViewNote";
    private static String newIncomeChooseCategoryBtn = "com.monefy.app.lite:id/keyboard_action_button";

    //category : Salary
    private static String newIncomeCategoryMenu = "com.monefy.app.lite:id/textCategoryName";

    public static void input1000AsIncome(){
        clickById(incomeKey1);
        clickById(incomeKey0);
        clickById(incomeKey0);
        clickById(incomeKey0);
    }

    public static void inputNewIncome(){
        input1000AsIncome();
        type(newIncomeNote, "first salary");
        clickById(newIncomeChooseCategoryBtn);
        chooseWithValue(newIncomeCategoryMenu, "Salary");
    }

}
