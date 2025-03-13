package learn.packOverflow.models;

import java.util.Date;
import java.util.Objects;

public class Answers {

    private int answerId;
    private User user;
    private Question question;
    private String body;
    private Date created;
    private Date updated;

    public Answers() {
    }

    public Answers(int answerId, User user, Question question, String body, Date created, Date updated) {
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
        Answers answers = (Answers) o;
        return answerId == answers.answerId && Objects.equals(user, answers.user) && Objects.equals(question, answers.question) && Objects.equals(body, answers.body) && Objects.equals(created, answers.created) && Objects.equals(updated, answers.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerId, user, question, body, created, updated);
    }
}
