package github.web;

import github.model.Constants;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

@Owner("evgdas")
@Feature("Создание ISSUE на Github")
@Tag("web")
public class IssueCreateTest extends BaseTest {
    private int issueNumber;

    @Test
    @DisplayName("Создание новой Issue")
    @Story("Проверка создания Issue")
    public void shouldCreateNewIssue() {
        parameter("Репозиторий", Constants.REPOSITORY);
        parameter("Заголовок Issue", Constants.ISSUE_TITLE);

        step("Открываем главную страницу репозитария", () -> {
            open("https://github.com/" + Constants.REPOSITORY);
        });
        step("Открываем страницу с задачами", () -> {
            $("a[href='/" + Constants.REPOSITORY + "/issues']").click();
        });
        step("Создаем новую задачу", () -> {
            $(".btn-primary > span").click();
            $("#issue_title").click();
            $("#issue_title").setValue(Constants.ISSUE_TITLE);
            $("#labels-select-menu").click();
            $(byText(Constants.LABEL)).click();
            $("#labels-select-menu").click();
            $(byText("Submit new issue")).click();

            //Получить номер созданной Issue:
            issueNumber = Integer.parseInt($(".gh-header-title span", 1).getText().replace("#", ""));
            parameter("Issue", issueNumber);
        });

        step("Проверяем наличие задачи с ID=" + issueNumber, () ->
        {
            $(withText("#" + issueNumber)).exists();
            $(".labels").shouldHave(text(Constants.LABEL));
        });
    }
}

