package part2.chapter6;

import org.junit.jupiter.api.Test;
import part2.GlobalTestSupport;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ArticleTest extends GlobalTestSupport {
    @Test
    void outputBasedAddCommentTest() {
        var sut = new Article();
        var text = "Comment text";
        var author = "John Doe";
        var now = LocalDateTime.now(clock);
        
        sut.addComment(text, author, now);

        assertThat(sut.getCommentCnt()).isEqualTo(1);
        assertThat(sut.getComment(0).getBody()).isEqualTo(text);
        assertThat(sut.getComment(0).getAuthor()).isEqualTo(author);
        assertThat(sut.getComment(0).getCreatedDt()).isEqualTo(now);
    }

    @Test
    void stateBasedAddCommentTest() {
        var sut = new Article();
        var text = "Comment text";
        var author = "John Doe";
        var now = LocalDateTime.now(clock);

        sut.addComment(text, author, now);

        sut.shouldContainNumberOfComments(1)
                .withComment(text, author, now);
    }

    @Test
    void communicationBasedAddCommentTest() {
        var sut = new Article();
        var text = "Comment text";
        var author = "John Doe";
        var now = LocalDateTime.now(clock);
        List<Comment> expectedComments = sut.getComments();

        sut.addComment(text, author, now);

        assertThat(sut.getComments()).isSameAs(expectedComments);
    }
}