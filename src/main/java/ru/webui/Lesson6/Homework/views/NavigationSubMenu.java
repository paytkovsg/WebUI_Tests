package ru.webui.Lesson6.Homework.views;

import org.openqa.selenium.WebDriver;
import ru.webui.Lesson6.Homework.Base.BaseView;
import ru.webui.Lesson6.Homework.Base.SubMenu;
import ru.webui.Lesson6.Homework.Base.SubMenuButtons;
import ru.webui.Lesson6.Homework.Pages.AllContactsPage;
import ru.webui.Lesson6.Homework.Pages.AllExpensesPage;
import ru.webui.Lesson6.Homework.Pages.AllMyProjectPage;
import ru.webui.Lesson6.Homework.enums.ExpenseSubMenuButtons;

public class NavigationSubMenu extends SubMenu {

    public NavigationSubMenu(WebDriver driver) {
        super(driver);
    }

    @Override
    public BaseView clickSubMenuButton(SubMenuButtons buttons) {
        if (buttons instanceof ExpenseSubMenuButtons) {
            switch ((ExpenseSubMenuButtons) buttons) {
                case EXPENSE_REQUEST:
                    driver.findElement(((ExpenseSubMenuButtons) buttons).getBy()).click();
                    return new AllExpensesPage(driver);
                case CONTACT_PERSON:
                    driver.findElement(((ExpenseSubMenuButtons)buttons).getBy()).click();
                    return new AllContactsPage(driver);
                case MY_PROJECTS:
                    driver.findElement(((ExpenseSubMenuButtons)buttons).getBy()).click();
                    return new AllMyProjectPage(driver);
                default:
                    throw new IllegalArgumentException("Not implemented yet");
            }
        } else {
            throw new IllegalArgumentException("Incorrect button type");
        }
    }
}
