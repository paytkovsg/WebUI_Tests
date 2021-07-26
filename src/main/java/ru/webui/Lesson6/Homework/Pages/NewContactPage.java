package ru.webui.Lesson6.Homework.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.webui.Lesson6.Homework.Base.BaseView;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class NewContactPage extends BaseView {

    @FindBy(xpath = ".//input[@name='crm_contact[lastName]']")
    private WebElement firstNameInput;

    @FindBy(xpath = ".//input[@data-ftid='crm_contact_firstName']")
    private WebElement nameInput;

    @FindBy(xpath = "//a[@title='Создать контактное лицо']")
    private WebElement buttonCrPerson;

    @FindBy(xpath = "//span[@class='select2-arrow']")
    private WebElement orgChoice;

    @FindBy(xpath = "//input[contains(@class, 'select2-input')]")
    private WebElement orgSearch;

    @FindBy(xpath = "//div[@class='select2-result-label']")
    private WebElement orgResultSearch;

    @FindBy(xpath = ".//input[@data-ftid='crm_contact_jobTitle']")
    private WebElement position;

    @FindBy(xpath = "//button[contains(.,'Сохранить и закрыть')]")
    private WebElement saveButton;

    public NewContactPage(WebDriver driver) {
        super(driver);
    }

    public NewContactPage enterFirstName(String firstName){
        firstNameInput.sendKeys(firstName);
        assertFalse(firstNameInput.getAttribute("value").isEmpty());
        return this;
    }
    public NewContactPage enterName (String name){
        nameInput.sendKeys(name);
        assertFalse(nameInput.getAttribute("value").isEmpty());
        return this;
    }

    public NewContactPage enterPosition(String positionName){
        position.sendKeys(positionName);
        assertFalse(position.getAttribute("value").isEmpty());
        return this;
    }

    public NewContactPage inputOrgChoice(String orgName){
        orgChoice.click();
        wait10.until(ExpectedConditions.visibilityOf(orgSearch));
        orgSearch.sendKeys(orgName);
        wait10.until(ExpectedConditions.visibilityOf(orgResultSearch));
        orgSearch.sendKeys(Keys.ENTER);
        return this;
    }

    public AllContactsPage saveNewContact(){
        saveButton.click();
        return new AllContactsPage(driver);
    }
    @Step(value = "Заполнение полей")
    public NewContactPage fillFieldsCreateContact (String firstName, String name, String orgName, String positionName){
        enterFirstName(firstName);
        enterName(name);
        inputOrgChoice(orgName);
        enterPosition(positionName);
        return this;
    }






}
