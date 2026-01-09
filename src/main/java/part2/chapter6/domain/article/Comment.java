package part2.chapter6.domain.article;

import java.time.LocalDateTime;
import java.util.Objects;

public class Comment {
    private String body;
    private String author;
    private LocalDateTime createdDt;

    public Comment(String body, String author, LocalDateTime createdDt) {
        this.body = body;
        this.author = author;
        this.createdDt = createdDt;
    }

    public String getBody() {
        return body;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getCreatedDt() {
        return createdDt;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Comment comment)) return false;
        return Objects.equals(body, comment.body) && Objects.equals(author, comment.author) && Objects.equals(createdDt, comment.createdDt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, author, createdDt);
    }
}
