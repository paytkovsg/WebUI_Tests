package ru.webui.Lesson6.Homework.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.webui.Lesson6.Homework.Base.BaseView;

public class LoginPage extends BaseView {

    @FindBy(xpath = ".//input[@id='prependedInput']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@id='prependedInput2']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[@name='_submit']")
    private WebElement loginButton;


    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @Step(value = "Ввести логин {login}")
    public LoginPage enterLogin (String login){
        inputLogin.sendKeys(login);
        return this;
    }
    @Step(value = "Ввести пароль {password}")
    public LoginPage enterPassword (String password){
        inputPassword.sendKeys(password);
        return this;
    }

    @Step(value = "Нажать кнопку входа")
    public HomePage clickButton (){
        loginButton.click();
        return new HomePage(driver);
    }
    // Для использования в других тестах
    @Step(value = "Auth with login = {login}, password = {password}")
    public HomePage authoriseScenario(String login, String password) {
        inputLogin.sendKeys(login);
        inputPassword.sendKeys(password);
        loginButton.click();
        return new HomePage(driver);
    }


}
