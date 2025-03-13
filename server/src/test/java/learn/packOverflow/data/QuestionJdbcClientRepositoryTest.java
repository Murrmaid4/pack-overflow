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
        List<Question> expected = List.of(TestHelper.existingQuestion);

        List<Question> actual = repository.findAll();

        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByUser() {
        User existingUser = TestHelper.existingUser;
        List<Question> expected = List.of(TestHelper.existingQuestion);

        List<Question> actual = repository.findByUser(existingUser.getUserId());

        assertNotNull(actual);
        assertEquals(expected, actual);

    }
    @Test
    void shouldNotFindByMissingUser() {
        int nonexistentUserId = 999;
        List<Question> expected = List.of();

        List<Question> actual = repository.findByUser(nonexistentUserId);
        
        assertEquals(expected, actual);

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