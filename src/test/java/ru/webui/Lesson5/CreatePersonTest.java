package ru.webui.Lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CreatePersonTest {
    private static WebDriver driver;
    private static final String LOGIN_PAGE = "https://crm.geekbrains.space/user/login";
    private static final String USER_LOGIN = "Applanatest1";
    private static final String USER_PASSWORD = "Student2020!";
    static final String menuCont = "//ul[contains(@class, 'nav-multilevel')]/li[contains(., 'Контрагенты')]";
    static final String subMenuCont = "//li[@data-route='crm_contact_index']/a";
    static final String buttonCrPerson = "//a[@title='Создать контактное лицо']";
    static final String orgChoice = "//span[@class='select2-arrow']";
    static final String orgSearch = "//input[contains(@class, 'select2-input')]";
    static final String orgResultSearch = "//div[@class='select2-result-label']";
    static final String saveButton = "//button[contains(.,'Сохранить и закрыть')]";
    static final String message = "//div[contains(@class, 'alert-success')]";


    @BeforeAll
    public static void setupWebDriverManager(){
        WebDriverManager.chromedriver().setup();
        setUpDriverSession();
        login();
    }
    @AfterAll
    public static void tearDown() {
        if(driver !=null){
            driver.quit();
        }
    }

    @Test
    @DisplayName("Проверка успешного входа")
    public void loginСheck(){
        WebElement username = driver.findElement(By.xpath(".//li[@id='user-menu']/a"));
        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath(".//li[@id='user-menu']/a")));
        Assertions.assertEquals(username.getText(), "Applanatest1 Applanatest1 Applanatest1");
    }
    @Test
    @DisplayName("Проверка создания контактного лица")
    public void CheckCreateAContact(){
        Actions actions = new Actions(driver);
        WebElement menuContrag = driver.findElement(By.xpath(menuCont));
        WebElement subMenuContact = driver.findElement(By.xpath(subMenuCont));
        actions
                .moveToElement(menuContrag)
                .moveToElement(subMenuContact)
                .click(subMenuContact)
                .build()
                .perform();
        WebElement buttonCreatePerson = driver.findElement(By.xpath(buttonCrPerson));
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath(buttonCrPerson)));
        Assertions.assertTrue(buttonCreatePerson.isDisplayed());
        WebElement inscriptionContacts = driver.findElement(By.xpath("//ul[contains(@class, 'breadcrumb')]/li[contains(., 'Контактные лица')]"));
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//ul[contains(@class, 'breadcrumb')]/li[contains(., 'Контактные лица')]")));
        Assertions.assertEquals(inscriptionContacts.getText(), "Контактные лица");
        buttonCreatePerson.click();

        WebElement firstNameInput = driver.findElement(By.xpath(".//input[@name='crm_contact[lastName]']"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath(".//input[@name='crm_contact[lastName]']")));
        firstNameInput.sendKeys("Пятков");
        assertFalse(firstNameInput.getAttribute("value").isEmpty());
        WebElement nameInput = driver.findElement(By.xpath(".//input[@data-ftid='crm_contact_firstName']"));
        nameInput.sendKeys("Сергей");
        assertFalse(nameInput.getAttribute("value").isEmpty());

        WebElement choiceOrganization = driver.findElement(By.xpath(orgChoice));
        choiceOrganization.click();
        WebElement searchOrganization = driver.findElement(By.xpath(orgSearch));
        searchOrganization.sendKeys("1234");
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath(orgResultSearch)));
        searchOrganization.sendKeys(Keys.ENTER);

        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//input[@data-ftid='crm_contact_jobTitle']")));

        WebElement position = driver.findElement(By.xpath(".//input[@data-ftid='crm_contact_jobTitle']"));
        position.sendKeys("Tester");
        assertFalse(position.getAttribute("value").isEmpty());
        WebElement savedContact = driver.findElement(By.xpath(saveButton));
        savedContact.click();
        WebElement messageCreate = driver.findElement(By.xpath(message));
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(message)));
        Assertions.assertTrue(messageCreate.isDisplayed());//проверяем появилось ли сообщение
    }

    private static void login(){
        driver.get(LOGIN_PAGE);
        WebElement loginTextInput = driver.findElement(By.xpath(".//input[@id='prependedInput']"));
        loginTextInput.sendKeys(USER_LOGIN);
        WebElement passTextInput = driver.findElement(By.xpath(".//input[@id='prependedInput2']"));
        passTextInput.sendKeys(USER_PASSWORD);
        WebElement loginButton = driver.findElement(By.xpath(".//button[@name='_submit']"));
        loginButton.click();

    }
    private static void setUpDriverSession(){
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

}
