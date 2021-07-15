package ru.webui.Lesson6.Homework.enums;

import org.openqa.selenium.By;
import ru.webui.Lesson6.Homework.Base.SubMenuButtons;

public enum ExpenseSubMenuButtons implements SubMenuButtons {

    EXPENSE_REQUEST(".//span[@class='title' and text()='Заявки на расходы']"),
    CONTACT_PERSON(".//span[@class='title' and text()='Контактные лица']"),
    MY_PROJECTS(".//span[@class='title' and text()='Мои проекты']");

//    BUSINESS_TRIPS("TODO"),
//    CENTER_COSTS("TODO"),
//    BUSINESS_CONTRACTS("TODO");

    private final By by;

    ExpenseSubMenuButtons(String xpath) {
        this.by = By.xpath(xpath);
    }

    public By getBy() {
        return by;
    }
}
