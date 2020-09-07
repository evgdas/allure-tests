package github.web;

import com.codeborne.selenide.logevents.SelenideLogger;
import github.steps.LoginStep;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import utils.helpers.ConfigGithub;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static utils.helpers.AttachmentsHelper.*;
import static utils.helpers.DriverHelper.configSelenide;
import static utils.helpers.DriverHelper.getConsoleLogs;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class BaseTest {
    final static ConfigGithub configGithub = ConfigFactory.newInstance().create(ConfigGithub.class);

    @BeforeAll
    static void initConfig() {
        configSelenide();
        SelenideLogger.addListener("allure", new AllureSelenide()
                .savePageSource(true)
                .screenshots(true));

        LoginStep.successfulLogin(configGithub.login(), configGithub.password());
    }

    @AfterEach
    public void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        closeWebDriver();
    }
}
