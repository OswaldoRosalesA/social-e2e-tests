package microservices.book.domain;

public class ScoreResponse {

    private final int score;

    // Empty constructor for JSON (de)serialization
    public ScoreResponse(){
        this.score = 0;
    }

    public ScoreResponse(final int score){
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "ScoreResponse{" +
                "score=" + score +
                '}';
    }

}
