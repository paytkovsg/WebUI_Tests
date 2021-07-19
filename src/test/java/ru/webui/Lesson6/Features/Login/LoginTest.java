package ru.webui.Lesson6.Features.Login;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import ru.webui.Lesson6.Base.BaseTest;
import ru.webui.Lesson6.Homework.Pages.LoginPage;

import static ru.webui.Lesson6.Confiq.Configuration.*;

@Feature("Login")
public class LoginTest extends BaseTest {
    @Test
    @Description("Проверка входа в систему с существующим пользователем")
    public void loginWithBaseUserTest() {
        new LoginPage(driver)
                .enterLogin(STUDENT_LOGIN)
                .enterPassword(STUDENT_PASSWORD)
                .clickButton()
                .checkUrl(BASE_URL);
    }


}
