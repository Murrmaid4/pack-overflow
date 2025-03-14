package learn.packOverflow.domain;

import learn.packOverflow.data.QuestionRepository;
import learn.packOverflow.data.TestHelper;
import learn.packOverflow.models.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

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