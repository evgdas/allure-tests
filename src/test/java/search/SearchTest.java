package search;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

@Owner("evgdas")
@Feature("Проверка поиска")
@Tag("web")
public class SearchTest extends BaseTest {
    @Test
    @DisplayName("Поиск страницы selenide на Google.com")
    public void searchSelenideInGoogle() {
        parameter("Строка поиска","selenide");

        step("Открытие страницы google.com", () -> {
            open("https://google.com");
        });
        step("Поиск selenide в google", () -> {
            $(By.name("q")).val("selenide").pressEnter();});
        step("Проверка результатов поиска", () -> {
            $$("#res .g").shouldHave(sizeGreaterThan(1));
            $("#res .g").shouldBe(visible).shouldHave(
                    text("Selenide:"),
                    text("selenide.org"));
        });
    }

    @Test
    @DisplayName("Поиск страницы selenide на Yandex.ru")
    public void searchSelenideInYandex() {
        parameter("Строка поиска","selenide");

        step("Открытие страницы yandex.ru", () -> {
            open("https://yandex.ru");
        });
        step("Поиск selenide в yandex", () -> {
            $("#text").val("selenide").pressEnter();});
        step("Проверка результатов поиска", () -> {
            $$(".serp-item").shouldHave(sizeGreaterThan(1));
            $("#search-result").shouldBe(visible).shouldHave(
                    text("Selenide: лаконичные и стабильные UI тесты на Java"),
                    text("selenide.org"));
        });
    }
}
