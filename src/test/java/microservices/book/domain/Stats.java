package microservices.book.domain;

import java.util.Collections;
import java.util.List;

public class Stats {

    private final Long userId;
    private final int score;
    private final List<String> badges;

    // Empty constructor JSON deserializer
    Stats(){
        userId = null;
        score = 0;
        badges = Collections.emptyList();
    }

    public Long getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }

    public List<String> getBadges() {
        return badges;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "userId=" + userId +
                ", score=" + score +
                ", badges=" + badges +
                '}';
    }
}
