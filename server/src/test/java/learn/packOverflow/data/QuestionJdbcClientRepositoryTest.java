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
        int sizeBeforeAdd = repository.findAll().size();

        Question actual = repository.create(beforeAdd);

        assertNotNull(actual);
        assertEquals(2, actual.getQuestionId());
        assertEquals(sizeBeforeAdd + 1, repository.findAll().size());
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

        boolean actual = repository.update(existingQuestion);

        assertTrue(actual);
        assertEquals(updatedBody, repository.findById(existingQuestion.getQuestionId()).getBody());
        assertEquals(existingQuestion, repository.findById(existingQuestion.getQuestionId()));
        //how to test this when the update is set at the exact second of creation
        //make a class that has static method that returns local date now
        //mockbean that class in test
        //use that class to get local date now in code
        //when my localdate time.get now then return whatever i set as the localdate time
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
        int sizeBeforeDelete = repository.findAll().size();
        boolean expected = true;

        boolean actual = repository.deleteById(TestHelper.existingQuestion.getQuestionId());

        assertTrue(actual);
        assertEquals(expected, actual);
        assertEquals(sizeBeforeDelete - 1, repository.findAll().size());
    }
    @Test
    void shouldNotDeleteMissingId() {
        int sizeBeforeDelete = repository.findAll().size();
        boolean expected = false;

        boolean actual = repository.deleteById(TestHelper.nonexistentId);

        assertFalse(actual);
        assertEquals(expected, actual);
        assertEquals(sizeBeforeDelete, repository.findAll().size());
    }
}