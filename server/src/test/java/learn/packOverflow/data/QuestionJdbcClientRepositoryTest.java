package learn.packOverflow.data;

import learn.packOverflow.models.Question;
import learn.packOverflow.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    @Nested
    public class FindTests {
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
            List<Question> expected = List.of();

            List<Question> actual = repository.findByUser(TestHelper.nonexistentId);

            assertEquals(expected, actual);

        }

        @Test
        void shouldFindByKeywordInTitle() {
            Set<Question> expected = Set.of(TestHelper.existingQuestion);

            Set<Question> actual = repository.findByKeyword("moving");

            assertEquals(expected, actual);
        }

        @Test
        void shouldFindByKeywordInBody() {
            Set<Question> expected = Set.of(TestHelper.existingQuestion);

            Set<Question> actual = repository.findByKeyword("fishing");

            assertEquals(expected, actual);
        }

        @Test
        void shouldFindByKeywordInBodyAndTitle() {
            Set<Question> expected = Set.of(TestHelper.existingQuestion);

            Set<Question> actual = repository.findByKeyword("Nova");

            assertEquals(expected, actual);
        }

        @Test
        void shouldNotFindByMissingKeyword() {
            Set<Question> expected = Set.of();

            Set<Question> actual = repository.findByKeyword("florida");

            assertEquals(expected, actual);
        }

        @Test
        void shouldFindById(){
            Question expected = TestHelper.existingQuestion;

            Question actual = repository.findById(expected.getQuestionId());

            assertNotNull(actual);
            assertEquals(expected, actual);
        }
        @Test
        void shouldNotFindByMissingQuestionId(){
            Question expected = null;

            Question actual = repository.findById(TestHelper.nonexistentId);

            assertNull(actual);
            assertEquals(expected, actual);
        }
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
    void shouldUpdate() {
        Question existingQuestion = new Question();
        existingQuestion.setQuestionId(TestHelper.existingQuestion.getQuestionId());
        existingQuestion.setUser(TestHelper.existingQuestion.getUser());
        existingQuestion.setTitle(TestHelper.existingQuestion.getTitle());
        String updatedBody = "never mind, i'm getting a job";
        existingQuestion.setBody(updatedBody);
        existingQuestion.setCreated(TestHelper.existingQuestion.getCreated());
        existingQuestion.setUpdated(TestHelper.existingQuestion.getUpdated());

        boolean actual = repository.update(existingQuestion);

        assertTrue(actual);
        assertEquals(updatedBody, repository.findById(existingQuestion.getQuestionId()).getBody());
        assertEquals(existingQuestion, repository.findById(existingQuestion.getQuestionId()));
    }
    @Test
    void shouldNotUpdateMissingId(){
        Question failUpdate = new Question();
        failUpdate.setQuestionId(TestHelper.nonexistentId);
        failUpdate.setUser(TestHelper.existingQuestion.getUser());
        failUpdate.setTitle(TestHelper.existingQuestion.getTitle());
        String updatedBody = "never mind, i'm getting a job";
        failUpdate.setBody(updatedBody);
        failUpdate.setCreated(TestHelper.existingQuestion.getCreated());
        failUpdate.setUpdated(TestHelper.existingQuestion.getUpdated());
        boolean expected = false;

        boolean actual = repository.update(failUpdate);

        assertFalse(actual);
        assertEquals(expected, actual);
        assertNull(repository.findById(failUpdate.getQuestionId()));
    }

    @Test
    void deleteById() {
    }
}