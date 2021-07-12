package ru.webui.Lesson3.Homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CreatePerson {
    private static WebDriver driver;
    private static final String LOGIN_PAGE = "https://crm.geekbrains.space/user/login";
    private static final String USER_LOGIN = "Applanatest1";
    private static final String USER_PASSWORD = "Student2020!";


    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();//запускаем хром-драйвер

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();//разворачиваем окно
        driver.get(LOGIN_PAGE);

        //здесь слипы на ожидания метять не стал
        //Заполняем необходимые поля данными
        login();

        driver.get("https://crm.geekbrains.space/contact/");
        WebElement buttonCreatePerson = driver.findElement(By.xpath(".//a[@title=\"Создать контактное лицо\"]"));
        buttonCreatePerson.click();
        Thread.sleep(2000);
        WebElement firstNameInput = driver.findElement(By.xpath(".//input[@name=\"crm_contact[lastName]\"]"));
        firstNameInput.sendKeys("Пятков4");
        WebElement nameInput = driver.findElement(By.xpath(".//input[@data-ftid=\"crm_contact_firstName\"]"));
        nameInput.sendKeys("Сергей");

        WebElement choiceOrganization = driver.findElement(By.xpath(".//div[@class=\"select2-container select2 input-widget\"]/a"));
        choiceOrganization.click();
        WebElement searchOrganization = driver.findElement(By.xpath(".//div[@id=\"select2-drop\"]/div/input"));
        searchOrganization.sendKeys("1234");
        Thread.sleep(2000);
        searchOrganization.sendKeys(Keys.ENTER);

        WebElement position = driver.findElement(By.xpath("//input[@data-ftid=\"crm_contact_jobTitle\"]"));
        position.sendKeys("Tester");
        driver.findElement(By.xpath(".//button[@class=\"btn btn-success action-button\"]")).click();

//        driver.quit();//закрываем браузер

    }
    private static void login(){
        driver.get(LOGIN_PAGE);
        WebElement loginTextInput = driver.findElement(By.xpath(".//input[@id=\"prependedInput\"]"));
        loginTextInput.sendKeys(USER_LOGIN);
        WebElement passTextInput = driver.findElement(By.xpath(".//input[@id=\"prependedInput2\"]"));
        passTextInput.sendKeys(USER_PASSWORD);
        WebElement loginButton = driver.findElement(By.xpath(".//button[@name='_submit']"));
        loginButton.click();

    }

}
