package learn.packOverflow.data;

import learn.packOverflow.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import learn.packOverflow.data.TestHelper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserJdbcClientRepositoryTest {


    @Autowired
    JdbcClient client;

    @Autowired
    UserJdbcClientRepository repository;

    User USER1 = new User(1, "user1", "password1", "email", "Brandi", "Murray");
    User USER2 = new User(2, "user2", "password2", "email2", "Kyle", "Murray");

//    @BeforeEach
//    void setup() {
//        client.sql("call set_known_good_state();").update();
//    }




    @Nested
    public class FindByUsername {
        @Test
        void happyPath() {
            User actual = repository.findByUsername("user1");

            assertNotNull(actual);

        }

        @Test
        void doesNotFind() {
            User actual = repository.findByUsername("notARealUsername");

            assertNull(actual);
        }


    }

    @Test
    void findById() {
        User actual = repository.findById(1);

        assertNotNull(actual);

    }

    @Test
    void create() {
        User toAdd = TestHelper.makeUser();


        User actual = repository.create(toAdd);

        assertEquals(3, actual.getUserId());

    }

    @Test
    void findAll() {
        List<User> expected = List.of(
                USER1, USER2
        );

        List<User> actual = repository.findAll();

        assertEquals(expected, actual);

    }
}