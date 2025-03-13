package learn.packOverflow.data;

import learn.packOverflow.models.User;

public class TestHelper {
    public static User makeUser() {
        return new User(3, "testing", "testPassword","email@email.com","aurora","newman");
    }


}
