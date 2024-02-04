package practiceWithSelenium.sm2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class LoginPageTest extends AbstractTest {

    //        Тесты:
//        1) Логин в систему не вводя ни логин, ни пароль. На странице появляется ошибка,
//        нужно проверить её текст
//        2) Более сложный тест на редактирование dummy
//        (здесь будет важно добавить методы для поиска ID dummy по имени,
//        чтобы сохранить его в переменную и потом в завершении теста использовать для проверки). Сценарий теста:
//        - Открываем модальное окно для редактирования dummy
//                - Редактируем имя
//                - Сохраняем
//                - Проверяем в таблице Dummies по ID, что имя изменилось
//        3) Тест на проверку модального окна Credentials
//        - Открыть модальное окно на созданном Dummy
//                - Проверить заголовок и статический контент
//        driver.quit();


    @Test
    @DisplayName("Проверка текста об ошибке в сплывающем окне(разные вариации)")
        // DONE 1
    void getAuthorizationWithOutParams() {
        driver.get("https://test-stand.gb.ru/login");
        LoginPage loginPage = new LoginPage(driver);
        //  loginPage.loginInSystem("NotCorrect","NotCorrect");
        loginPage.LoginInSystemWithOutParams();
        WebElement checkElement = driver.findElement
                (By.xpath("//h2[@class='svelte-uwkxn9']"));
        //      (By.xpath("div[@class='error-block svelte-uwkxn9']/p[contains(text(),'Invalid credentials.')]"));
        Assertions.assertEquals("401", checkElement.getText());
//    Assertions.assertEquals("Invalid credentials",checkElement.getText());
    }

    @Test
    @DisplayName("Тест на простое изменение имени")
    void getDummyNameRewritten() throws InterruptedException {
        driver.get("https://test-stand.gb.ru/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginInSystem(getLogin(), getPassword());

        RemakeNamePage remakeNamePage = new RemakeNamePage(driver);
        remakeNamePage.openFirstDummyCardAndSaveNewName("New Dummy2");

        WebElement searchElement = driver.
                findElement(By.xpath("//div[@class='container svelte-tv8alb']/h1"));
        Assertions.assertEquals("New Dummy2", searchElement.getText());

    }

    @Test
    @DisplayName("Тест на проверку Id измененного названия заголовка")
    void getDummyIDRewritten() throws InterruptedException {
        driver.get("https://test-stand.gb.ru/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginInSystem(getLogin(), getPassword());
        RemakeNamePage remakeNamePage = new RemakeNamePage(driver);
        remakeNamePage.openFirstDummyCardAndSaveNewName("New Dummy2");
        remakeNamePage.openProfile();
        WebElement webElement = driver.findElement(By.cssSelector("tbody tr td"));
        Assertions.assertEquals("96751", webElement.getText());
    }


    @Test
    @DisplayName("Пример с семинара")
    void loginTest() throws InterruptedException {
        driver.get("https://test-stand.gb.ru/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginInSystem("OOlega", "8b8ea6e167");
        List<WebElement> searchList = driver.findElements(By.partialLinkText("Hello"));
        Assertions.assertEquals(1, searchList.size());

        MainPage mainPage = new MainPage(driver);
        mainPage.createPost();

        List<WebElement> title = driver.findElements(By.xpath("//*[@type='text']"));
        Assertions.assertEquals(1, title.size());// поиск элемента Title

        CreatePostPage createPostPage = new CreatePostPage(driver);
        createPostPage.savePost("This is New post created by PageObject", "New PageObject Post");

        Thread.sleep(5000L);
        ElementPage elementPage = new ElementPage(driver);
        elementPage.deletePost();
    }
}