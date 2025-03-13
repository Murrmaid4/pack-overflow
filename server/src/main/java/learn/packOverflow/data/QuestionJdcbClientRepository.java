package learn.packOverflow.data;

import learn.packOverflow.models.Question;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class QuestionJdcbClientRepository implements QuestionRepository{
    private final JdbcClient jdbcClient;

    public QuestionJdcbClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    private final String SELECT = """
            select question_id, `user_id`, `title`, `body`, `created`, `updated`
            from questions
            """;

    @Override
    public List<Question> findAll() {
        return List.of();
    }

    @Override
    public List<Question> findByUser() {
        return List.of();
    }

    @Override
    public List<Question> findByKeyword() {
        return List.of();
    }

    @Override
    public Question create(Question question) {
        final String sql = "insert into questions (`user_id`, `title`, `body`, `created`, `updated`) values (:`user_id`, :`title`, :`body`, :`created`, :`updated`);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcClient.sql(sql)
                .param("`user_id`", question.getUser().getUserId())
                .param("`title`", question.getTitle())
                .param("`body`", question.getBody())
                .param("`created`", question.getCreated())
                .param("`updated`", question.getUpdated())
                .update(keyHolder, "question_id");
        if (rowsAffected <= 0){
            return null;
        }
        question.setQuestionId(keyHolder.getKey().intValue());;
        return question;


    }

    @Override
    public boolean update(Question question) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
