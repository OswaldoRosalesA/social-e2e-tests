package microservices.book.domain;

public class User {

    private final Long id;
    private final String alias;

    // Empty constructor for JSON deserializer
    User(){
        this.id = null;
        this.alias = null;
    }

    public Long getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                '}';
    }
}
