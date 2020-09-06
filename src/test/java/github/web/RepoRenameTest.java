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
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@Owner("evgdas")
@Feature("Проверка переименования проекта Github")
@Tag("web")
public class RepoRenameTest extends BaseTest {
    String newRepoName = "testname" + (int) (Math.random() * 1000000);

    @Test
    @Story("Проверка переименования проекта Github")
    @DisplayName("Проверка переименования проекта Github")
    public void renameProject() {
        parameter("Репозиторий", Constants.REPOSITORY);

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
