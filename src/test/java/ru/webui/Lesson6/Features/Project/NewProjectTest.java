package ru.webui.Lesson6.Features.Project;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Test;
import ru.webui.Lesson6.Base.BaseTest;
import ru.webui.Lesson6.Confiq.Configuration;
import ru.webui.Lesson6.Homework.Pages.AllMyProjectPage;
import ru.webui.Lesson6.Homework.Pages.LoginPage;
import ru.webui.Lesson6.Homework.enums.ExpenseSubMenuButtons;
import ru.webui.Lesson6.Homework.enums.NavigationBarTabs;

import java.util.UUID;

@Feature("CreateNewProject")
@Severity(SeverityLevel.CRITICAL)
public class NewProjectTest extends BaseTest {
    @Test
    @Description("Проверка создания нового проекта")
    public void createNewProjectTest(){
        AllMyProjectPage projectScreen = (AllMyProjectPage) new LoginPage(driver)
                .authoriseScenario(Configuration.STUDENT_LOGIN, Configuration.STUDENT_PASSWORD)
                .getPageNavigation()
                .moveCursorToNavigationTab(NavigationBarTabs.PROJECTS)
                .clickSubMenuButton(ExpenseSubMenuButtons.MY_PROJECTS);

        projectScreen
                .clickOnCreateProject()
                .fillFieldsInput("Проект: "+ UUID.randomUUID().toString(), "1234", "Пятков Сергей",
                        1, "Юзеров Юзер", "Юзеров Юзер", "Юзеров Юзер")
                .saveNewProject()
                .checkNewProjectPopUp();





    }
}
