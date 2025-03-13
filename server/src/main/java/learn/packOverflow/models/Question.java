package learn.packOverflow.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Question {

    private int questionId;
    private User user;
    private String title;
    private String body;
    private LocalDate created;
    private LocalDate updated;


    public Question() {
    }

    public Question(int questionId, User user, String title, String body, LocalDate created, LocalDate updated) {
        this.questionId = questionId;
        this.user = user;
        this.title = title;
        this.body = body;
        this.created = created;
        this.updated = updated;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Question questions = (Question) o;
        return questionId == questions.questionId && Objects.equals(user, questions.user) && Objects.equals(title, questions.title) && Objects.equals(body, questions.body) && Objects.equals(created, questions.created) && Objects.equals(updated, questions.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, user, title, body, created, updated);
    }
}
