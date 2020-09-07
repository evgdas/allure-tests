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
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@Owner("evgdas")
@Feature("Работа с репозитариеми на Github")
@Tag("web")
public class WorkWithRepoTest extends BaseTest {
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

    @Test
    @Story("Проверка наличия репозитария")
    @DisplayName("Проверка наличия репозитария GitHub")
    public void projectExists() {
        parameter("Репозиторий", Constants.REPOSITORY);

        step("Открываем страницу репозитария", () -> {
            open("https://github.com/" + Constants.REPOSITORY);
        });
        step("Проверяем факт открытия репозитария", () -> {
            assertThat(title(), equalTo(Constants.REPOSITORY));
        });
    }

    @Test
    @Story("Проверка переименования проекта Github")
    @DisplayName("Проверка переименования проекта Github")
    public void renameProject() {
        parameter("Репозиторий", Constants.REPOSITORY);
        String newRepoName = "testname" + (int) (Math.random() * 1000000);

        step("Открываем страницу репозитария", () -> {
            open("https://github.com/" + Constants.REPOSITORY + "/settings");
        });
        step("Изменяем наименование репозитария", () -> {
            $("#rename-field").setValue(newRepoName);
            $("auto-check").shouldHave(text("is available."));
            $(byText("Rename")).click();
        });
        step("Проверяем наименование репозитария", () -> {
            assertThat(title(), containsString(newRepoName));
        });
        step("Возвращаем старое наименование", () -> {
            open("https://github.com/evgdas/" + newRepoName + "/settings");
            $("#rename-field").setValue(Constants.REPOSITORY.substring(7));
            $("auto-check").shouldHave(text("is available."));
            $(byText("Rename")).click();
            assertThat(title(), containsString(Constants.REPOSITORY));
        });
    }
}

