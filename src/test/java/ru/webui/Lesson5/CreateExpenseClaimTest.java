package ru.webui.Lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CreateExpenseClaimTest {
    private static WebDriver driver;
    private static final String LOGIN_PAGE = "https://crm.geekbrains.space/user/login";
    private static final String USER_LOGIN = "Applanatest1";
    private static final String USER_PASSWORD = "Student2020!";
    private static final String menuCosts = "//ul[contains(@class, 'nav-multilevel')]/li[contains(., 'Расходы')]";
    private static final String subMenuExpCl = "//li[@data-route='crm_expense_request_index']/a";
    private static final String createExpBtn = "//a[@title='Создать заявку на расход']";
    private static final String saveBtn = "//button[@class='btn btn-success action-button']";
    private static final String message = "//div[@class='message']";


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
    @DisplayName("Проверка создания заявки на расходы")
    public void CheckCreateExpenseClaim(){
        Actions actions = new Actions(driver);
        WebElement menuCostsMain = driver.findElement(By.xpath(menuCosts));
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(menuCosts)));
        WebElement subMenuExpClMain = driver.findElement(By.xpath(subMenuExpCl));
        actions
                .moveToElement(menuCostsMain)
                .moveToElement(subMenuExpClMain)
                .click(subMenuExpClMain)
                .build()
                .perform();
        WebElement createExpButton = driver.findElement(By.xpath(createExpBtn));
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(createExpBtn)));
        Assertions.assertTrue(createExpButton.isDisplayed());
        WebElement pageAllExpense = driver.findElement(By.xpath("//ul[contains(@class, 'breadcrumb')]/li[contains(., 'Заявки на расходы')]"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//ul[contains(@class, 'breadcrumb')]/li[contains(., 'Заявки на расходы')]")));
        Assertions.assertEquals(pageAllExpense.getText(), "Заявки на расходы");
        createExpButton.click();

        WebElement pageCreateExp = driver.findElement(By.xpath("//h1[@class='user-name']"));
        Assertions.assertEquals(pageCreateExp.getText(), "Создать заявку на расход");

        //заполняем назначение
        WebElement appointment = driver.findElement(By.xpath("//textarea[@data-ftid='crm_expense_request_description']"));
        appointment.sendKeys("Назначение расхода");

        //выбираем подразделение
        Select unitSelection = new Select(driver.findElement(By.xpath("//select[@data-ftid='crm_expense_request_businessUnit']")));
        unitSelection.selectByVisibleText("Research & Development");

        //статью расходов
        Select expenseItem = new Select(driver.findElement(By.xpath("//select[@data-ftid='crm_expense_request_expenditure']")));
        expenseItem.selectByVisibleText("01101 - ОС: вычислительная техника инфраструктуры");

        //запрашиваемая сумма. Сначала чистим поле, потом заполняем
        WebElement sumPlan = driver.findElement(By.xpath("//input[@data-ftid='crm_expense_request_sumPlan']"));
        sumPlan.clear();
        sumPlan.sendKeys("2500");

        //выбираем тип расхода
        Select expenseType = new Select(driver.findElement(By.xpath("//select[@data-ftid='crm_expense_request_type']")));
        expenseType.selectByVisibleText("Наличный");

        // Чекбокс уведомления о переносе даты оплаты. По умолчанию установлен, снимаем
        WebElement dateChangeNotifyCheckBox = driver.findElement(By.xpath("//input[@data-ftid='crm_expense_request_dateChangeNotify']"));
        dateChangeNotifyCheckBox.click();

        //Устанавливаем планируемую дату
        WebElement hasDatePicker = driver.findElement
                (By.xpath("//div[preceding-sibling::div[child::label[@class='required']]]//input[@class='datepicker-input  hasDatepicker']"));
        hasDatePicker.click();
        driver.findElement(By.xpath("//a[text()='29']")).click();

        //выбираем тип платежа(с ндс или нет)
        Select nds = new Select(driver.findElement(By.xpath("//select[@data-ftid='crm_expense_request_nds']")));
        nds.selectByVisibleText("Без НДС");

        //сохраняем заявку и проверяем сообщение
        WebElement saveExButton = driver.findElement(By.xpath(saveBtn));
        saveExButton.click();
        WebElement messageCreateEx = driver.findElement(By.xpath(message));
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(message)));
        Assertions.assertEquals(messageCreateEx.getText(), "Заявка на расход сохранена");
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
