package alfabank;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.helpers.ConfigDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static utils.drivers.CustomWebDriver.configDriver;
import static utils.helpers.EnvironmentHelper.isDriverFromFile;

@Owner("evgdas")
@Feature("Работа с сайтом Альфабанка")
@Tag("alfabank")
public class AlfaBankTest extends BaseTest {
    final String URL_ALFA_BANK = "https://alfabank.ru/";

    @Test
    @DisplayName("Проверка наличия депозита (зайти с главной странице)")
    @Story("Проверка депозитов")
    public void isDepozitPrezent() {
        step("Открываем главную страницу", () -> {
            open(URL_ALFA_BANK);
        });
        step("Переход по меню на депозиты", () -> {
            $(byTitle("Вклады")).click();
            $x("//button[p='Депозиты']").click();
        });
        step("Проверяем наличие депозита", () -> {
            $(byText("Альфа-Вклад")).shouldBe();
        });
    }

    @Test
    @DisplayName("Проверка открытия страницы Накопительные счета (зайти с главной странице)")
    @Story("Проверка открытия страниц")
    public void isAccountPrezent() {
        step("Открываем главную страницу ", () -> {
            open(URL_ALFA_BANK);
        });
        step("Переход по меню на страницу Накопительные счета", () -> {
            $(byTitle("Вклады")).click();
            $x("//button[p='Накопительные счета']").click();
        });
        step("Проверка наличия счета", () -> {
            $("#alfa-account").shouldHave(text("Альфа-Счёт"));
        });
    }
}
