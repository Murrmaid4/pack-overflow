package learn.packOverflow.data;

import learn.packOverflow.models.Answer;

import java.util.List;
import org.springframework.jdbc.core.simple.JdbcClient;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


@Repository
public class AnswerJdbcClientRepository implements AnswerRepository {
    JdbcClient client;

    public AnswerJdbcClientRepository(JdbcClient client) {
        this.client = client;
    }

    @Override
    public List<Answer> findByQuestionId(int questionId) {
        return List.of();
    }

    @Override
    public Answer create(Answer answer) {
        return null;
    }

    @Override
    public boolean update(Answer answer) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
