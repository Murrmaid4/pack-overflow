package learn.packOverflow.data;

import learn.packOverflow.models.Question;
import learn.packOverflow.models.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestHelper {
    public static User existingUser = new User(1, "user1", "password1", "email1", "Brandi", "Murray");
    public static Question existingQuestion = new Question(1, existingUser, "moving to nova scotia", "do i need to pack my own fishing gear or can i buy it in nova scotia?", LocalDateTime.of(2025, 03, 24, 11, 54, 15), LocalDateTime.of(2025, 03, 25, 8, 15, 22));
    public static User makeUser() {
        return new User(3, "testing", "testPassword","email@email.com","aurora","newman");
    }
    public static int nonexistentId = 999;


}
