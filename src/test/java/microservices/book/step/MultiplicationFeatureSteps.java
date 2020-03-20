package microservices.book.step;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import microservices.book.domain.AttemptResponse;
import microservices.book.domain.Stats;
import microservices.book.util.MultiplicationApplication;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiplicationFeatureSteps {

    private MultiplicationApplication app;
    private AttemptResponse lastAttemptResponse;
    private Stats lastStatsResponse;

    public MultiplicationFeatureSteps() {
        this.app = new MultiplicationApplication();
    }

    @Before
    public void cleanUp() {
        app.deleteData();
    }

    @When("^the user ([^\\s]+) sends (\\d+) ([^\\s]+) attempts$")
    public void the_user_sends_attempts(final String userAlias,
                                        final int attempts,
                                        final String rightOrWrong) throws Throwable {

        int attemptsSent = IntStream.range(0, attempts)
                .mapToObj(i -> app.sendAttempt(userAlias, 10, 10, "right".equals(rightOrWrong) ? 100 : 258))
                .peek(response -> lastAttemptResponse = response)
                .mapToInt(response -> response.isCorrect() ? 1 : 0)
                .sum();

        assertThat(attemptsSent).isEqualTo("right".equals(rightOrWrong) ? attempts : 0)
                .withFailMessage("Error sending attempts to the application");

    }

    @Then("^the user gets a response indicating the attempt is ([^\\s]+)$")
    public void the_user_gets_a_response_indicating_the_attempt_is_rightOrWrong(String rightOrWrong) throws Throwable {
        assertThat(lastAttemptResponse.isCorrect())
                .isEqualTo("right".equals(rightOrWrong))
                .withFailMessage("Expecting a response with a "
                        + rightOrWrong + " attempt");
    }

    @And("^the user gets (\\d+) points for the attempt$")
    public void the_user_gets_points_for_the_attempt(int points) throws Throwable {
        long attemptId = lastAttemptResponse.getId();
        Thread.currentThread().sleep(2000);
        int score = app.getScoreForAttempt(attemptId).getScore();
        assertThat(score).isEqualTo(points);
    }

    @And("^the user gets the ([^\\s]+) badge$")
    public void the_user_gets_the_badge(String badgeType) throws Throwable {
        long userId = lastAttemptResponse.getUser().getId();
        Thread.currentThread().sleep(200);
        lastStatsResponse = app.getStatsForUser(userId);
        List<String> userBadges = lastStatsResponse.getBadges();
        assertThat(userBadges).contains(badgeType);
    }

    @And("^the user does not get any badge$")
    public void the_user_does_not_get_any_badge() throws Throwable {
        long userId = lastAttemptResponse.getUser().getId();
        Stats stats = app.getStatsForUser(userId);
        List<String> userBadges = stats.getBadges();
        if (stats.getScore() == 0) {
            assertThat(stats.getBadges()).isNullOrEmpty();
        } else {
            assertThat(userBadges).isEqualTo(lastStatsResponse.getBadges());
        }
    }

    public MultiplicationApplication getApp() {
        return app;
    }
}
