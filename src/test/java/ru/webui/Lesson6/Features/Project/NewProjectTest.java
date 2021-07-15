package ru.webui.Lesson6.Features.Project;

import org.junit.jupiter.api.Test;
import ru.webui.Lesson6.Base.BaseTest;
import ru.webui.Lesson6.Confiq.Configuration;
import ru.webui.Lesson6.Homework.Pages.AllMyProjectPage;
import ru.webui.Lesson6.Homework.Pages.LoginPage;
import ru.webui.Lesson6.Homework.enums.ExpenseSubMenuButtons;
import ru.webui.Lesson6.Homework.enums.NavigationBarTabs;

import java.util.UUID;

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
                .clickOrgChoice()
                .enterOrgSearch("1234")
                .pushEnterOrgResultSearch()
                .clickContactPerson()
                .enterContactPerson("Пятков Сергей")
                .pushEnterContactPersonSearch()
                .selectBusinessUnit(1)
                .selectCurator("Юзеров Юзер")
                .selectProjectManager("Юзеров Юзер")
                .selectManager("Юзеров Юзер")
                .saveNewProject()
                .checkNewProjectPopUp();





    }
}
