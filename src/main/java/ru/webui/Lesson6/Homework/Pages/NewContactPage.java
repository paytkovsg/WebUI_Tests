package ru.webui.Lesson6.Homework.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.webui.Lesson6.Homework.Base.BaseView;

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
        return this;
    }
    public NewContactPage enterName (String name){
        nameInput.sendKeys(name);
        return this;
    }

    public NewContactPage enterPosition(String positionName){
        position.sendKeys(positionName);
        return this;
    }

    public NewContactPage clickOrgChoice(){
        orgChoice.click();
        return this;
    }

    public NewContactPage enterOrgSearch(String orgName){
        orgSearch.sendKeys(orgName);
        return this;
    }

    public NewContactPage pushEnterOrgResultSearch(){
        orgResultSearch.click();
        return new NewContactPage(driver);
    }

    public AllContactsPage saveNewContact(){
        saveButton.click();
        return new AllContactsPage(driver);
    }






}
