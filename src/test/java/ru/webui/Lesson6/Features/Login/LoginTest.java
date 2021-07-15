package ru.webui.Lesson6.Features.Login;

import ru.webui.Lesson6.Base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.webui.Lesson6.Homework.Pages.LoginPage;

import static ru.webui.Lesson6.Confiq.Configuration.*;

public class LoginTest extends BaseTest {
    @Test
    @DisplayName("Проверка входа в систему")
    public void loginWithBaseUserTest() {
        new LoginPage(driver)
                .enterLogin(STUDENT_LOGIN)
                .enterPassword(STUDENT_PASSWORD)
                .clickButton()
                .checkUrl(BASE_URL);
    }


}
