import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MainTest {


    @Test
    void userCanLogin()
    {
        LoginPage loginPage = new LoginPage();
        loginPage.login("ruslanio@mail.com", "IlovePolytech", "Руслан Cупонькин", false);
        loginPage.login("rusik.03@mail.ru", "Technopolis", "Руслан Cупонькин", true);
    }
}

class LoginPage
{

    public void login(String email, String password, String expectedUsername, boolean expectedResult)
    {
        final String nameOfErrorDiv = "//div[@class='input-e login_error']";
        final String error = "Неправильно указан логин и/или пароль";

        open("https://ok.ru");

        $(By.xpath("//*[@id='field_email']")).setValue(email);
        $(By.xpath("//*[@id='field_password']")).setValue(password);
        $(By.xpath("//*[@value='Войти в Одноклассники']")).click();
        assertEquals(error, $(By.xpath(nameOfErrorDiv)).text());
    }
}