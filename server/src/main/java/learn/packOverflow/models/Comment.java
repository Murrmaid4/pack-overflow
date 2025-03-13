package learn.packOverflow.models;

import java.util.Date;
import java.util.Objects;

public class Comment {

    private int commentId;
    private User user;
    private Question question;
    private Answer answer;
    private String body;
    private Date created;


    public Comment() {
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Comment comments = (Comment) o;
        return commentId == comments.commentId && Objects.equals(user, comments.user) && Objects.equals(question, comments.question) && Objects.equals(answer, comments.answer) && Objects.equals(body, comments.body) && Objects.equals(created, comments.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, user, question, answer, body, created);
    }
}
