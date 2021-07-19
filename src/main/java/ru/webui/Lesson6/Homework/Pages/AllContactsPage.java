package ru.webui.Lesson6.Homework.Pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.webui.Lesson6.Homework.Base.BaseView;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllContactsPage extends BaseView {

    @FindBy(xpath = "//a[@title='Создать контактное лицо']")
    private WebElement buttonCrPerson;

    public AllContactsPage(WebDriver driver) {
        super(driver);
    }

    public NewContactPage clickOnCreateContact(){
        wait10.until(ExpectedConditions.visibilityOf(buttonCrPerson));
        Assertions.assertTrue(buttonCrPerson.isDisplayed());
        buttonCrPerson.click();
        return new NewContactPage(driver);

    }
    public AllContactsPage checkNewContactPopUp() {
        String message = wait10.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
                "div[class='message']"))).getText();
        assertTrue(message.contains("Контактное лицо сохранено"));
        return this;
    }

}
