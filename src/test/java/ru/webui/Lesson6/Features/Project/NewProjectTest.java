package ru.webui.Lesson6.Features.Project;

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
    public void createNewProjectTest(){
        AllMyProjectPage projectScreen = (AllMyProjectPage) new LoginPage(driver)
                .authoriseScenario(Configuration.STUDENT_LOGIN, Configuration.STUDENT_PASSWORD)
                .getPageNavigation()
                .moveCursorToNavigationTab(NavigationBarTabs.PROJECTS)
                .clickSubMenuButton(ExpenseSubMenuButtons.MY_PROJECTS);

        projectScreen
                .clickOnCreateProject()
                .enterName("Проект: "+ UUID.randomUUID().toString())
                .inputOrgChoice("1234")
                .clickContactPerson("Пятков Сергей")
                .selectBusinessUnit(1)
                .selectCurator("Юзеров Юзер")
                .selectProjectManager("Юзеров Юзер")
                .selectManager("Юзеров Юзер")
                .saveNewProject()
                .checkNewProjectPopUp();





    }
}
