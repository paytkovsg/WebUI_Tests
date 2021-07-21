package ru.webui.Lesson6.Features.Navigation;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.webui.Lesson6.Base.BaseTest;
import ru.webui.Lesson6.Confiq.Configuration;
import ru.webui.Lesson6.Homework.Pages.LoginPage;
import ru.webui.Lesson6.Homework.enums.NavigationBarTabs;

@Feature("Навигация")

public class NavigationTest extends BaseTest {
    @ParameterizedTest
    @Description("Проверка навигации по Submenu")
    @MethodSource("navigationTabProvider")
    public void checkMainNavigationTabsTest(NavigationBarTabs barTab) {
        new LoginPage(driver)
                .authoriseScenario(Configuration.STUDENT_LOGIN, Configuration.STUDENT_PASSWORD)
                .getPageNavigation()
                .checkTabVisibility(barTab);

    }

    static NavigationBarTabs[] navigationTabProvider() {
        return NavigationBarTabs.values();
    }

}
