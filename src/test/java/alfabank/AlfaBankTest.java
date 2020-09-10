package alfabank;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Owner("evgdas")
@Feature("Работа с сайтом Альфабанка")
@Tag("alfabank")
public class AlfaBankTest extends BaseTest {
    final String URL_ALFA_BANK = "https://alfabank.ru/";

    @Test
    @DisplayName("Проверка наличия архивного депозита (зайти с главной странице)")
    @Story("Проверка депозитов")
    void checkSizeDepozit() {
        step("Открываем главную страницу", () -> {
            open(URL_ALFA_BANK);
        });
        step("Переход по меню на депозиты", () -> {
            $(byTitle("Вклады")).click();
            $(byTitle("Депозиты")).click();
            $(byText("Архивные счета и депозиты")).click();
            $(byText("Депозиты")).click();
        });
        step("Проверяем наличие депозита", () -> {
            $(byText("Победа+")).shouldBe();
        });
    }

    @Test
    @DisplayName("Проверка открытия страницы Накопительные счета (зайти с главной странице)")
    @Story("Проверка открытия страниц")
    public void openPageDepozitInsurance() {
        step("Открываем главную страницу ", () -> {
            open(URL_ALFA_BANK);
        });
        step("Переход по меню на страницу Накопительные счета", () -> {
            $(byTitle("Вклады")).click();
            $(byTitle("Депозиты")).click();
            $(byText("Накопительные счета")).click();
        });
        step("Проверка наличия счета", () -> {
            $("#alfa-account").shouldHave(text("Альфа-Счёт"));
        });
    }
}
