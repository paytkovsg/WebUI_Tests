package ru.webui.Lesson6.Features.Login;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import ru.webui.Lesson6.Base.BaseTest;
import ru.webui.Lesson6.Homework.Pages.HomePage;
import ru.webui.Lesson6.Homework.Pages.LoginPage;
import ru.webui.Lesson6.Homework.helpers.ScreenshotMaker;

import static ru.webui.Lesson6.Confiq.Configuration.*;

@Feature("Login")
@Severity(SeverityLevel.BLOCKER)
public class LoginTest extends BaseTest {
    @Test
    @Description( "Проверка входа в систему с существующим пользователем")
    public void loginWithBaseUserTest() {
        new LoginPage(driver)
                .enterLogin(STUDENT_LOGIN)
                .enterPassword(STUDENT_PASSWORD)
                .clickButton()
                .checkUrl(BASE_URL);
        ScreenshotMaker.makeScreenshot(driver,"login.jpg");
    }

    @Test
    @Description("Логин с помощью cookie")
    public void loginWithCookieTest() {
        driver.get(BASE_URL);
        driver
                .manage()
                .addCookie(new Cookie("BAPID", "c06ded2dea21a32e33f50811c141fa2d"));

        driver.get(BASE_URL);

        new HomePage(driver)
                .checkUrl(BASE_URL);
    }


}
