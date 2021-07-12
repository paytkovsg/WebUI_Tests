package ru.webui.Lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class CreateProjectTest {
    private static WebDriver driver;
    private static final String LOGIN_PAGE = "https://crm.geekbrains.space/user/login";
    private static final String USER_LOGIN = "Applanatest1";
    private static final String USER_PASSWORD = "Student2020!";
    private static final String menuProject = "//ul[contains(@class, 'nav-multilevel')]/li[contains(., 'Проекты')]";
    private static final String subMenuProject = "//li[@data-route='crm_project_my']/a";
    private static final String createPrBtn = "//a[@title='Создать проект']";
    private static final String orgChoice = ".//a[@class='select2-choice select2-default']";
    private static final String orgSearch = ".//div[@id='select2-drop']/div/input";
    private static final String orgResultSearch = "//div[@class='select2-result-label']/span";
    private static final String contactMain = ".//div[@class='select2-container select2']/a";
    private static final String contactMainSearch = ".//div[@id='select2-drop']/div/input";
    private static final String resultSearchContact = "//div[@class='select2-result-label']";
    private static final String saveBtn = "//button[contains(.,'Сохранить и закрыть')]";
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
    @DisplayName("Проверка создания проекта")
    public void createProject(){
        Actions actions = new Actions(driver);
        WebElement menuProjectMain = driver.findElement(By.xpath(menuProject));
        WebElement subMenuMyProject = driver.findElement(By.xpath(subMenuProject));
        actions
                .moveToElement(menuProjectMain)
                .moveToElement(subMenuMyProject)
                .click(subMenuMyProject)
                .build()
                .perform();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(createPrBtn)));
        WebElement createProjectButton = driver.findElement(By.xpath(createPrBtn));
        Assertions.assertTrue(createProjectButton.isDisplayed());
        WebElement pageMyProjects = driver.findElement(By.xpath("//ul[contains(@class, 'breadcrumb')]/li[contains(., 'Мои проекты')]"));
        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//ul[contains(@class, 'breadcrumb')]/li[contains(., 'Мои проекты')]")));
        Assertions.assertEquals(pageMyProjects.getText(), "Мои проекты");
        createProjectButton.click();

        //Наименование проекта
        WebElement projectName = driver.findElement(By.xpath(".//input[@data-ftid='crm_project_name']"));
        projectName.sendKeys("Project: " + UUID.randomUUID().toString());

        //выбираем организацию
        WebElement fieldOrg = driver.findElement(By.xpath(orgChoice));
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(orgChoice)));
        fieldOrg.click();

        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(orgSearch)));
        driver.findElement(By.xpath(orgSearch)).sendKeys("1234");

        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(orgResultSearch)));
        fieldOrg.sendKeys(Keys.ENTER);

        //выбираем контактное лицо
        WebElement contactMen = driver.findElement(By.xpath(contactMain));
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contactMain)));
        driver.findElement(By.xpath(contactMain)).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contactMainSearch)));
        driver.findElement(By.xpath(contactMainSearch)).sendKeys("Пятков Сергей");
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(resultSearchContact)));
        contactMen.sendKeys(Keys.ENTER);

        //выбираем подразделение
        Select unitSelection = new Select(driver.findElement(By.xpath(".//select[@data-ftid='crm_project_businessUnit']")));
        unitSelection.selectByVisibleText("Research & Development");

        //Куратор проекта
        Select curator = new Select(driver.findElement(By.xpath(".//select[@name='crm_project[curator]']")));
        curator.selectByVisibleText("Юзеров Юзер");

        //Руководитель проекта
        Select projectManager = new Select(driver.findElement(By.xpath(".//select[@name='crm_project[rp]']")));
        projectManager.selectByVisibleText("Юзеров Юзер");

        // Менеджер
        Select manager = new Select(driver.findElement(By.xpath(".//select[@name='crm_project[manager]']")));
        manager.selectByValue("8");

        //Кликаем сохранить и закрыть
        WebElement saveCloses = driver.findElement(By.xpath(saveBtn));
        saveCloses.click();
        WebElement pageAllProjects = driver.findElement(By.xpath(".//h1[@class='oro-subtitle']"));
        Assertions.assertEquals(pageAllProjects.getText(), "Все Проекты");
        WebElement messageCreatePr = driver.findElement(By.xpath(message));
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(message)));
        Assertions.assertEquals(messageCreatePr.getText(), "Проект сохранен");

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

