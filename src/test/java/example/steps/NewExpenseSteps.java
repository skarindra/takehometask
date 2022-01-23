package example.steps;

/**
 * Created by sekarayukarindra.
 */
public class NewExpenseSteps extends BaseSteps {

    private static String expenseKey1 = "com.monefy.app.lite:id/buttonKeyboard1";
    private static String expenseKey0 = "com.monefy.app.lite:id/buttonKeyboard0";

    private static String newExpenseNote = "com.monefy.app.lite:id/textViewNote";
    private static String newExpenseChooseCategoryBtn = "com.monefy.app.lite:id/keyboard_action_button";

    //category : Bills
    private static String newExpenseCategoryMenu = "com.monefy.app.lite:id/textCategoryName";

    private static void input100AsExpense(){
        clickById(expenseKey1);
        clickById(expenseKey0);
        clickById(expenseKey0);
    }

    public static void inputNewExpense(){
        input100AsExpense();
        type(newExpenseNote, "phone bills");
        clickById(newExpenseChooseCategoryBtn);
        chooseWithValue(newExpenseCategoryMenu, "Bills");
    }
}
