package learn.packOverflow.models;

import java.util.Date;
import java.util.Objects;

public class Answer {

    private int answerId;
    private User user;
    private Question question;
    private String body;
    private Date created;
    private Date updated;

    public Answer() {
    }

    public Answer(int answerId, User user, Question question, String body, Date created, Date updated) {
        this.answerId = answerId;
        this.user = user;
        this.question = question;
        this.body = body;
        this.created = created;
        this.updated = updated;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return answerId == answer.answerId && Objects.equals(user, answer.user) && Objects.equals(question, answer.question) && Objects.equals(body, answer.body) && Objects.equals(created, answer.created) && Objects.equals(updated, answer.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerId, user, question, body, created, updated);
    }
}
