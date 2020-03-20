package microservices.book.domain;

public class AttemptResponse {

    private final Long id;
    private final boolean correct;
    private final User user;

    // Empty constructor for JSON (de)serialization
    AttemptResponse() {
        id = null;
        user = null;
        correct = false;
    }

    public Long getId() {
        return id;
    }

    public boolean isCorrect() {
        return correct;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "AttemptResponse{" +
                "id=" + id +
                ", correct=" + correct +
                ", user=" + user +
                '}';
    }
}
