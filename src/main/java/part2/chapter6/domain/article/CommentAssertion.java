package part2.chapter6.domain.article;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentAssertion {
    private final List<Comment> actualComments;
    private Integer expectedCnt;

    protected CommentAssertion(List<Comment> actualComments) {
        this.actualComments = actualComments;
    }

    protected CommentAssertion shouldContainNumberOfComments(int cnt) {
        this.expectedCnt = cnt;
        assertThat(actualComments).hasSize(cnt);
        return this;
    }

    protected CommentAssertion withComment(String content, String author, LocalDateTime createdDt) {
        boolean found = actualComments.stream()
                .anyMatch(c -> c.getBody().equals(content) &&
                        c.getAuthor().equals(author) &&
                        c.getCreatedDt().equals(createdDt)
                );

        assertThat(found)
                .as("Comment with content='%s', author='%s' should exist", content, author)
                .isTrue();

        return this;
    }
}
