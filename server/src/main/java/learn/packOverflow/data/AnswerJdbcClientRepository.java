package learn.packOverflow.data;

import learn.packOverflow.data.mappers.AnswerMapper;
import learn.packOverflow.data.mappers.UserMapper;
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
        final String sql = """
              SELECT * from answers
                join questions q on answers.question_id = q.question_id
                  join user u on q.user_id=u.user_id
                  where q.question_id = ?;
        """;

        return client.sql(sql)
                .param(questionId)
                .query(new AnswerMapper())
                .list();


    }

    @Override
    public Answer create(Answer answer) {
    final String sql = "INSERT INTO `answer` (user_id, question_id, body, created, updated) VALUES (:user_id, :question_id, :body, :created, :updated)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = client.sql(sql)
                .param("user_id", answer.getUser().getUserId())
                .param("question_id", answer.getQuestion().getQuestionId())
                .param("body", answer.getBody())
                .param("created", answer.getCreated())
                .param("updated", answer.getUpdated())
                .update(keyHolder, "answer_id");

        if (rowsAffected <= 0) {
            return null;
        }

        answer.setAnswerId(keyHolder.getKey().intValue());
        return answer;
    }

    @Override
    public boolean update(Answer answer) {

        final String sql = """
                update answers
                set user_id = :user_id,
                    question_id = :question_id,
                    body = :body,
                    created = :created,
                    updated = :updated
                where answer_id = :answer_id;
                """;


        return client.sql(sql)
                .param("user_id",answer.getUser().getUserId())
                .param("question_id",answer.getQuestion().getQuestionId())
                .param("body",answer.getBody())
                .param("created",answer.getCreated())
                .param("updated",answer.getUpdated())
                .update() > 0;

    }

    @Override
    public boolean deleteById(int id) {
        final String sql = """
                delete from answers
                where answer_id = ?;
                """;

        return client.sql(sql)
                .param(id)
                .update() > 0;
    }
}
