package ru.webui.Lesson6.Homework.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.webui.Lesson6.Homework.Base.BaseView;

public class NewProjectPage extends BaseView {

    @FindBy(xpath = ".//input[@data-ftid='crm_project_name']")
    private WebElement projectName;

    @FindBy(xpath = ".//span[@class='select2-arrow']")
    private WebElement orgChoice;

    @FindBy(xpath = "//input[contains(@class, 'select2-input')]")
    private WebElement orgSearch;

    @FindBy(xpath = "//div[@class='select2-result-label']")
    private WebElement orgResultSearch;

    @FindBy(xpath = ".//div[@class='select2-container select2']/a")
    private WebElement contactPerson;

    @FindBy(xpath = ".//div[@id='select2-drop']/div/input")
    private WebElement contactPersonSearch;

    @FindBy(xpath = "//div[@class='select2-result-label']")
    private WebElement resultSearchContact;

    @FindBy(xpath = "//button[contains(.,'Сохранить и закрыть')]")
    private WebElement saveProjectButton;

    @FindBy(xpath = ".//select[@data-ftid='crm_project_businessUnit']")
    private WebElement unitSelection; //подразделение

    @FindBy(xpath = ".//select[@name='crm_project[curator]']")
    private WebElement projectCurator;

    @FindBy(xpath = ".//select[@name='crm_project[rp]']")
    private WebElement projectManager;

    @FindBy(xpath = ".//select[@name='crm_project[manager]']")
    private WebElement manager;

    @FindBy(xpath = "//button[@class='btn btn-success action-button']")
    private WebElement saveProject;

    public NewProjectPage(WebDriver driver) {
        super(driver);
    }

    public NewProjectPage enterName(String nameProject){
        projectName.sendKeys(nameProject);
        return this;
    }
     public NewProjectPage inputOrgChoice(String orgName){
        orgChoice.click();
        wait10.until(ExpectedConditions.visibilityOf(orgSearch));
        orgSearch.sendKeys(orgName);
        wait10.until(ExpectedConditions.visibilityOf(orgResultSearch));
        orgSearch.sendKeys(Keys.ENTER);
        return this;
    }

    public NewProjectPage clickContactPerson(String namePerson){
        contactPerson.click();
        wait10.until(ExpectedConditions.visibilityOf(contactPersonSearch));
        contactPersonSearch.sendKeys(namePerson);
        wait10.until(ExpectedConditions.visibilityOf(resultSearchContact));
        contactPersonSearch.sendKeys(Keys.ENTER);
        return this;
    }

    public NewProjectPage selectBusinessUnit(int value){
        Select businessUnitDropDown = new Select(unitSelection);
        businessUnitDropDown.selectByValue(String.valueOf(value));
        return this;
    }
     public NewProjectPage selectCurator(String valueC){
        Select projectCuratorDropDown = new Select(projectCurator);
        projectCuratorDropDown.selectByVisibleText(valueC);
        return this;
    }
    public NewProjectPage selectProjectManager(String valuePM){
        Select projectManagerDropDown = new Select(projectManager);
        projectManagerDropDown.selectByVisibleText(valuePM);
        return this;
    }
    public NewProjectPage selectManager(String valueM){
        Select managerDropDown = new Select(manager);
        managerDropDown.selectByVisibleText(valueM);
        return this;
    }
    @Step(value = "Нажать на кнопку «Сохранить и закрыть»")
    public AllMyProjectPage saveNewProject(){
        saveProject.click();
        return new AllMyProjectPage(driver);
    }

    @Step(value = "Заполнение полей")
    public NewProjectPage fillFieldsInput (String nameProject, String orgName, String namePerson, int value, String valueC,
                                           String valuePM, String valueM) {
        enterName(nameProject);
        inputOrgChoice(orgName);
        clickContactPerson(namePerson);
        selectBusinessUnit(value);
        selectCurator(valueC);
        selectProjectManager(valuePM);
        selectManager(valueM);
        return this;
     }

}
