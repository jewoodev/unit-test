package part2.chapter6.domain.article;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Article {
    private List<Comment> comments;
    private CommentAssertion commentAssertion;

    public Article() {
        this.comments = new ArrayList<>();
        this.commentAssertion = new CommentAssertion(this.comments);
    }

    public void addComment(String body, String author, LocalDateTime commentDt) {
        var comment = new Comment(body, author, commentDt);
        comments.add(comment);
    }

    public int getCommentCnt() {
        return this.comments.size();
    }

    public Comment getComment(int index) {
        return this.comments.get(index);
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public CommentAssertion shouldContainNumberOfComments(int cnt) {
        return this.commentAssertion.shouldContainNumberOfComments(cnt);
    }

    public CommentAssertion withComment(String body, String author, LocalDateTime commentDt) {
        return this.commentAssertion.withComment(body, author, commentDt);
    }
}
