package ru.webui.Lesson6.Features.Expencec;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import ru.webui.Lesson6.Confiq.Configuration;
import ru.webui.Lesson6.Homework.enums.ExpenseSubMenuButtons;
import ru.webui.Lesson6.Homework.enums.NavigationBarTabs;
import ru.webui.Lesson6.Homework.Pages.AllExpensesPage;
import ru.webui.Lesson6.Homework.Pages.LoginPage;
import ru.webui.Lesson6.Base.BaseTest;

@Feature("Создание расхода")
public class NewExpensesTest extends BaseTest {

    private static final int EXPENSE_SUM = 10000;

    @Test
    public void createNewExpensePositiveTest() {
        AllExpensesPage expensesScreen = (AllExpensesPage) new LoginPage(driver)
            .authoriseScenario(Configuration.STUDENT_LOGIN, Configuration.STUDENT_PASSWORD)
            .getPageNavigation()
            .moveCursorToNavigationTab(NavigationBarTabs.EXPENSES)
            .clickSubMenuButton(ExpenseSubMenuButtons.EXPENSE_REQUEST);

        expensesScreen
            .clickOnCreateNewExpenseButton()
            .enterDescription("test 1234")
            .selectBusinessUnit(1)
            .selectExpenditure(87)
            .setExpenseSum(EXPENSE_SUM)
            .clickNotifyDateChangedCheckBox()
            .selectDateInDatePicker(20)
            .clickSubmit()
            .checkNewExpensePopUp();
    }
}
