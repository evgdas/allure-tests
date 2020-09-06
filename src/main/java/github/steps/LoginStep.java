package github.steps;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginStep {

    @Step("Логин на github")
    public void successfulLogin(String loginName, String password) {
        open("https://github.com/login");

        $("#login_field").setValue(loginName);
        $("#password").setValue(password);
        $("input[name='commit']").click();

        $("body").shouldHave(text("Repositories"));
    }
}
