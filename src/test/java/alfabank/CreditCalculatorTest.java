package alfabank;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

@Owner("evgdas")
@Feature("Работа с сайтом Альфабанка")
@Tag("alfabank")
public class CreditCalculatorTest extends BaseTest {

    @Test
    @DisplayName("Проверка работы кредитного калькулятора")
    @Story("Проверка кредитного калькулятора")
    void checkCreditCalculator() {
        step("Открываем главную страницу", () -> {
            open(URL_ALFA_BANK);
        });
        step("Установка значений в поля калькулятора: Сумма, Количество лет", () -> {
            parameter("Сумма", 1500000);
            parameter("Количество лет", "1 год");

            $(byText("Сумма кредита")).sibling(0).click();
            $(byText("Сумма кредита")).sibling(0).setValue("1500000");
            $(byText("1 год")).closest("button").click();
        });
        step("Проверяем сумму", () -> {
            parameter("Сумма должна быть:", "132 318");

            $(by("data-test-id", "btnMinus")).parent().sibling(0).shouldBe(text("132 318 ₽"));
        });
    }
}
