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

@Owner("evgdas")
@Feature("Работа с сайтом Альфабанка")
@Tag("alfabank")
public class AlfaBankTest extends BaseTest {
    final String URL_ALFA_BANK = "https://alfabank.ru/";

    @Test
    @DisplayName("Проверка количества депозитов (зайти с главной странице)")
    @Story("Проверка депозитов")
    void checkSizeDepozit() {
        open(URL_ALFA_BANK);

        $(byTitle("Вклады")).click();
        $(byTitle("Депозиты")).click();
        $(byText("Архивные депозиты")).click();

        $$((".product-cell__cell-back")).shouldHaveSize(3);
    }

    @Test
    @DisplayName("Проверка открытия старницы Страхование вкладов (зайти с главной странице)")
    @Story("Проверка открытия страниц")
    public void openPageDepozitInsurance() {
        open(URL_ALFA_BANK);

        $(byTitle("Вклады")).click();
        //В задании найти ссылку используя parent
        $(byTitle("Страхование вкладов")).parent().lastChild().click();

        $("h1").shouldHave(text("Страхование вкладов"));
        $(".col-sm-8").shouldHave(text("АО «АЛЬФА-БАНК» является участником"));
    }
}
