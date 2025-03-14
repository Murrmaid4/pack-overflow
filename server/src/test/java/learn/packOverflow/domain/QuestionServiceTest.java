package learn.packOverflow.domain;

import learn.packOverflow.data.QuestionRepository;
import learn.packOverflow.data.TestHelper;
import learn.packOverflow.models.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class QuestionServiceTest {
    @MockBean
    QuestionRepository repository;

    @Autowired
    QuestionService service;

    @Test
    void findAll() {
    }

    @Test
    void shouldFindByUser() {
        List<Question> list = List.of(TestHelper.existingQuestion);
        Result<List<Question>> expected = new Result<>();
        expected.setPayload(list);
        when(repository.findByUser(TestHelper.existingUser.getUserId())).thenReturn(List.of(TestHelper.existingQuestion));

        Result<List<Question>> actual = service.findByUser(TestHelper.existingUser.getUserId());

        assertEquals(expected, actual);
    }
    @Test
    void shouldNotFindByMissingUser(){
        Result<List<Question>> expected = new Result<>();
        expected.addErrorMessage("No questions found", ResultType.NOT_FOUND);
        when(repository.findByUser(TestHelper.nonexistentId)).thenReturn(List.of());

        Result<List<Question>> actual = service.findByUser(TestHelper.nonexistentId);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindByInvalidUserId(){
        Result<List<Question>> expected = new Result<>();
        int invalidId = -5;
        expected.addErrorMessage("Invalid user ID", ResultType.INVALID);

        Result<List<Question>> actual = service.findByUser(invalidId);

        assertEquals(expected, actual);
    }

    @Test
    void findByKeyword() {
        Set<Question> questionSet = Set.of(TestHelper.existingQuestion);
        Result<Set<Question>> expected = new Result<>();
        expected.setPayload(questionSet);
        String keyword = TestHelper.existingQuestion.getBody().substring(10);
        when(repository.findByKeyword(keyword)).thenReturn(questionSet);

        Result<Set<Question>> actual = service.findByKeyword(keyword);

        assertEquals(expected, actual);
    }
    @Test
    void shouldNotFindByNullKeyword() {
        Result<Set<Question>> expected = new Result<>();
        expected.addErrorMessage("keyword required", ResultType.INVALID);
        String keyword = null;

        Result<Set<Question>> actual = service.findByKeyword(keyword);

        assertEquals(expected, actual);
    }
    @Test
    void shouldNotFindByBlankKeyword() {
        Result<Set<Question>> expected = new Result<>();
        expected.addErrorMessage("keyword required", ResultType.INVALID);
        String keyword = "          ";

        Result<Set<Question>> actual = service.findByKeyword(keyword);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindByNonexistentKeyword() {
        Result<Set<Question>> expected = new Result<>();
        expected.addErrorMessage("No questions found", ResultType.NOT_FOUND);
        String keyword = "null";

        Result<Set<Question>> actual = service.findByKeyword(keyword);

        assertEquals(expected, actual);
    }

    @Test
    void findById() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}