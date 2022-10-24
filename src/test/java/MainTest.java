import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MainTest {

    LoginPage loginPage = new LoginPage();
    UserPage userPage = new UserPage();
    @Test
    void userCanLogin()
    {
        tryToLogin("technoPol181", "technoPolis20228", "technoPol18 technoPol118", false);
        tryToLogin("technoPol18", "technoPolis2022", "technoPol18 technoPol18", true);
    }

    private void tryToLogin(String email, String password, String usersFullName, boolean expectedResult){
        open("https://ok.ru");
        loginPage.login(email, password);
        userPage.checkUsersName(usersFullName, expectedResult);
        clearBrowserCookies();
    }
}

class LoginPage
{
    public void login(String email, String password)
    {
        $(By.xpath("//*[@id='field_email']")).setValue(email);
        $(By.xpath("//*[@id='field_password']")).setValue(password);
        $(By.xpath("//*[@value='Войти в Одноклассники']")).click();
    }
}

class UserPage{
    public void checkUsersName(String usersFullName, boolean expectedResult){
        if ($$(By.xpath("//*[@class = 'tico ellip']")).size() == 0)
            assertFalse(expectedResult);
        else
            assertEquals(usersFullName, $(By.xpath("//*[@class = 'tico ellip']")).text());
        clearBrowserCookies();
    }
}