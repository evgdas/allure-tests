package github.api;

import github.model.Constants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.TestInstance;
import utils.helpers.ConfigGithub;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class BaseApiTest {
    final RequestSpecification requestSpecification;
    final ResponseSpecification responseSpecification;
    final ConfigGithub configGithub = ConfigFactory.newInstance().create(ConfigGithub.class);

    public BaseApiTest() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(configGithub.baseUrl())
                .addHeader("Authorization", configGithub.token())
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .setBody("")
                .addFilter(new AllureRestAssured())
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(200)
                .build();
    }
}
