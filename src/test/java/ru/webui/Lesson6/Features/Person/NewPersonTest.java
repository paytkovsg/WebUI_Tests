package ru.webui.Lesson6.Features.Person;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import ru.webui.Lesson6.Base.BaseTest;
import ru.webui.Lesson6.Confiq.Configuration;
import ru.webui.Lesson6.Homework.Pages.AllContactsPage;
import ru.webui.Lesson6.Homework.Pages.LoginPage;
import ru.webui.Lesson6.Homework.enums.ExpenseSubMenuButtons;
import ru.webui.Lesson6.Homework.enums.NavigationBarTabs;

@Feature("CreateNewPerson")
public class NewPersonTest extends BaseTest {

    @Test
    @Description("Проверка создания контактного лица")
    public void createNewContactTest(){
        AllContactsPage contactsScreen = (AllContactsPage) new LoginPage (driver)
                .authoriseScenario(Configuration.STUDENT_LOGIN, Configuration.STUDENT_PASSWORD)
                .getPageNavigation()
                .moveCursorToNavigationTab(NavigationBarTabs.COUNTER_PARTIES)
                .clickSubMenuButton(ExpenseSubMenuButtons.CONTACT_PERSON);

        contactsScreen
                .clickOnCreateContact()
                .fillFieldsCreateContact("Пятков", "Сергей", "1234", "Tester" )
                .saveNewContact()
                .checkNewContactPopUp();

    }

}
