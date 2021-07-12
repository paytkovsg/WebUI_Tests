package ru.webui.Lesson3.Homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateProject {
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
        login();

        driver.get("https://crm.geekbrains.space/project/my");

        WebElement createProject = driver.findElement(By.xpath(".//a[@title=\"Создать проект\"]"));
        createProject.click();

        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath(".//input[@name=\"crm_project[name]\"]")));
        WebElement projectName = driver.findElement(By.xpath(".//input[@name=\"crm_project[name]\"]"));
        projectName.sendKeys("New project Пятков");

        //выбираем организацию
        driver.findElement(By.xpath(".//a[@class=\"select2-choice select2-default\"]")).click();
        WebElement choiceOrganization1 = driver.findElement(By.xpath(".//div[@id=\"select2-drop\"]/div/input"));
        choiceOrganization1.sendKeys("1234");
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@class=\"select2-result-label\"]/span")));
//        Thread.sleep(2000);
        choiceOrganization1.sendKeys(Keys.ENTER);

        //выбираем контактное лицо
        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath(".//div[@class=\"select2-container select2\"]/a")));
        driver.findElement(By.xpath(".//div[@class=\"select2-container select2\"]/a")).click();
        WebElement choiceContact = driver.findElement(By.xpath(".//div[@id=\"select2-drop\"]/div/input"));
        choiceContact.sendKeys("Пятков Сергей");
        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath(".//div[@id=\"select2-drop\"]/div/input")));
        choiceContact.sendKeys(Keys.ENTER);

        //выбираем подразделение
        Select unitSelection = new Select(driver.findElement(By.xpath(".//select[@data-ftid='crm_project_businessUnit']")));
        unitSelection.selectByVisibleText("Research & Development");

        //Куратор проекта
        Select curator = new Select(driver.findElement(By.xpath(".//select[@name=\"crm_project[curator]\"]")));
        curator.selectByVisibleText("Юзеров Юзер");

        //Руководитель проекта
        Select projectManager = new Select(driver.findElement(By.xpath(".//select[@name=\"crm_project[rp]\"]")));
        projectManager.selectByVisibleText("Юзеров Юзер");

        // Менеджер
        Select manager = new Select(driver.findElement(By.xpath(".//select[@name=\"crm_project[manager]\"]")));
//        manager.selectByVisibleText("Юзеров Юзер");
        manager.selectByValue("8");

        //Кликаем сохранить и закрыть
        driver.findElement(By.xpath(".//button[@class=\"btn btn-success action-button\"]")).click();

        driver.quit();//закрываем браузер

    }
    private static void login(){
        WebElement loginTextInput = driver.findElement(By.xpath(".//input[@id=\"prependedInput\"]"));
        loginTextInput.sendKeys(USER_LOGIN);
        WebElement passTextInput = driver.findElement(By.xpath(".//input[@id=\"prependedInput2\"]"));
        passTextInput.sendKeys(USER_PASSWORD);
        WebElement loginButton = driver.findElement(By.xpath(".//button[@name='_submit']"));
        loginButton.click();
    }
}
