package ru.webui.Lesson6.Homework.views;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ru.webui.Lesson6.Homework.Base.BaseView;
import ru.webui.Lesson6.Homework.Base.SubMenu;
import ru.webui.Lesson6.Homework.enums.NavigationBarTabs;

public class NavigationBar extends BaseView {

    public NavigationBar(WebDriver driver) {
        super(driver);
    }
        public SubMenu moveCursorToNavigationTab(NavigationBarTabs tab) {
            Actions actions = new Actions(driver);
            actions
                    .moveToElement(driver.findElement(tab.getBy()))
                    .build()
                    .perform();
            switch (tab) {
                case EXPENSES:
                    return new NavigationSubMenu(driver);
                case COUNTER_PARTIES:
                    return new NavigationSubMenu(driver);
                case PROJECTS:
                    return new NavigationSubMenu(driver);
                default:
                    throw new IllegalArgumentException("Another tabs currently not implemented");
            }
        }

        public NavigationBar checkTabVisibility(NavigationBarTabs tab) {
            Assertions.assertTrue(driver.findElement(tab.getBy()).isDisplayed());
            return this;
        }

}
