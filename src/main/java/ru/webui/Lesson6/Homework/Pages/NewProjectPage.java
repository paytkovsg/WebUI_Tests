package ru.webui.Lesson6.Homework.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ru.webui.Lesson6.Homework.Base.BaseView;

public class NewProjectPage extends BaseView {

    @FindBy(xpath = ".//input[@data-ftid='crm_project_name']")
    private WebElement projectName;

    @FindBy(xpath = ".//a[@class='select2-choice select2-default']")
    private WebElement orgChoice;

    @FindBy(xpath = ".//div[@id='select2-drop']/div/input")
    private WebElement orgSearch;

    @FindBy(xpath = "//div[@class='select2-result-label']/span")
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
    public NewProjectPage clickOrgChoice(){
        orgChoice.click();
        return this;
    }
    public NewProjectPage enterOrgSearch (String ornName){
        orgSearch.sendKeys(ornName);
        return this;
    }
    public NewProjectPage pushEnterOrgResultSearch (){
        orgResultSearch.click();
        return new NewProjectPage(driver);
    }

    public NewProjectPage clickContactPerson(){
        contactPerson.click();
        return this;
    }
    public NewProjectPage enterContactPerson (String namePerson){
        contactPersonSearch.sendKeys(namePerson);
        return this;
    }
    public NewProjectPage pushEnterContactPersonSearch(){
        resultSearchContact.click();
        return new NewProjectPage(driver);
    }
    public NewProjectPage selectBusinessUnit(int value){
        Select businessUnitDropDown = new Select(unitSelection);
        businessUnitDropDown.selectByValue(String.valueOf(value));
        return this;
    }
    public NewProjectPage selectCurator(String value){
        Select projectCuratorDropDown = new Select(projectCurator);
        projectCuratorDropDown.selectByVisibleText(value);
        return this;
    }
    public NewProjectPage selectProjectManager(String value){
        Select projectManagerDropDown = new Select(projectManager);
        projectManagerDropDown.selectByVisibleText(value);
        return this;
    }
    public NewProjectPage selectManager(String value){
        Select managerDropDown = new Select(manager);
        managerDropDown.selectByVisibleText(value);
        return this;
    }
    public AllMyProjectPage saveNewProject(){
        saveProject.click();
        return new AllMyProjectPage(driver);
    }

}
