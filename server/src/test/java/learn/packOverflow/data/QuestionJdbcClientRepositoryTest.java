package learn.packOverflow.data;

import learn.packOverflow.models.Question;
import learn.packOverflow.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class QuestionJdbcClientRepositoryTest {

    @Autowired
    JdbcClient jdbcClient;

    @Autowired
    QuestionRepository repository;

    @BeforeEach
    void setup() {
        jdbcClient.sql("call set_known_good_state();").update();
    }

    @Test
    void shouldFindAll() {
        User existingUser = new User(1, "test username 1", "password1", "email", "Brandi", "Murray");
        Question existingQuestion = new Question(1, existingUser, "moving to nova scotia", "do i need to pack my own fishing gear or can i buy it there?", LocalDate.of(2025, 03, 24), LocalDate.of(2025, 03, 25));
        List<Question> expected = List.of(existingQuestion);

        List<Question> actual = repository.findAll();

        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @Test
    void findByUser() {
    }

    @Test
    void findByKeyword() {
    }

    @Test
    void create() {
        Question beforeAdd = new Question();
        beforeAdd.setQuestionId(0);
        User testUser = new User(1, "testpassword", "testusername", "test@email.com");
        beforeAdd.setUser(testUser);
        beforeAdd.setTitle("Test title");
        beforeAdd.setBody("Test body");
        beforeAdd.setCreated(LocalDate.now().minusDays(1));
        beforeAdd.setUpdated(LocalDate.now());

        Question actual = repository.create(beforeAdd);

        assertNotNull(actual);
        assertEquals(2, actual.getQuestionId());


    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}