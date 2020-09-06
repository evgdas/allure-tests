package github.api;

import github.model.Constants;
import github.model.Issue;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Owner("evgdas")
@Feature("Проверка существования Issue")
@Tag("api")
public class IssueApiCheckTest extends BaseApiTest {
    private final int issueNumber = 1;
    private Issue issue = new Issue();

    @Test
    @DisplayName("Проверка существования Issue через API")
    @Story("Проверка существования Issue")
    public void isExistIssue() {
        parameter("Репозиторий", Constants.REPOSITORY);
        parameter("Заголовок Issue", Constants.ISSUE_TITLE);

        step("Проверяем наличие Issue №1 через API", () -> {
            issue = given(requestSpecification)
                    .when()
                    .get(Constants.REPOSITORY + "issues/" + issueNumber)
                    .then().spec(responseSpecification)
                    .extract()
                    .as(Issue.class);
            assertThat(issue.getTitle(), equalTo(Constants.ISSUE_TITLE));
            assertThat(issue.getLabels().get(0).getName(), equalTo(Constants.LABEL));
        });
    }
}