package alfabank;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static utils.helpers.AttachmentsHelper.*;
import static utils.helpers.DriverHelper.configSelenide;
import static utils.helpers.DriverHelper.getConsoleLogs;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class BaseTest {
    @BeforeAll
    static void setUp() {
        configSelenide();
        SelenideLogger.addListener("allure", new AllureSelenide()
                .savePageSource(true)
                .screenshots(true));
    }
    @AfterEach
    public void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        closeWebDriver();
    }
}
