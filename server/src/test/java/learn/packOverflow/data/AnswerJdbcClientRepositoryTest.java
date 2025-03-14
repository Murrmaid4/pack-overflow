package learn.packOverflow.data;

import learn.packOverflow.models.Answer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AnswerJdbcClientRepositoryTest {

    @Autowired
    JdbcClient client;

    @Autowired
    AnswerJdbcClientRepository repository;

    @BeforeEach
    void setup() {
        client.sql("call set_known_good_state();").update();
    }

    @Test
    void findByQuestionId() {
        List<Answer> actual = repository.findByQuestionId(1);
        assertNotNull(actual);
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