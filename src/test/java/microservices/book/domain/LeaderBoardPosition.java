package microservices.book.domain;

public class LeaderBoardPosition {


    private final Long userId;
    private final Long totalScore;

    // Empty constructor for JSON / JPA
    public LeaderBoardPosition() {
     this.userId = null;
     this.totalScore = null;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getTotalScore() {
        return totalScore;
    }

    @Override
    public String toString() {
        return "LeaderBoardPosition{" +
                "userId=" + userId +
                ", totalScore=" + totalScore +
                '}';
    }
}
