package github.web;

import github.model.Constants;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Owner("evgdas")
@Feature("Проверка наличия репозитария Github")
@Tag("web")
public class RepoPresentTest extends BaseTest {

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
}
