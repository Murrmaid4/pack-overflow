package learn.packOverflow.data;

import learn.packOverflow.data.mappers.QuestionsMapper;
import learn.packOverflow.models.Question;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class QuestionJdbcClientRepository implements QuestionRepository{
    private final JdbcClient jdbcClient;

    public QuestionJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    private final String SELECT = """
            select q.question_id, q.user_id, q.title, q.body, q.created, q.updated,
            u.username, u.password, u.email, u.first_name, u.last_name
            from questions q inner join user u  on q.user_id=u.user_id
            """;

    @Override
    public List<Question> findAll() {
        return jdbcClient.sql(SELECT + ";").query(new QuestionsMapper()).list();
    }

    @Override
    public List<Question> findByUser(int userId) {
        final String sql = SELECT + "where u.user_id = ?;";
        return jdbcClient.sql(sql)
                .param(userId)
                .query(new QuestionsMapper())
                .list();

    }

    @Override
    public Set<Question> findByKeyword(String keyword) {
        final String sql = SELECT + " WHERE title LIKE ? OR body LIKE ?;";
        return jdbcClient.sql(sql)
                .param("%" + keyword + "%")
                .param("%" + keyword + "%")
                .query(new QuestionsMapper())
                .set();
    }

    @Override
    public Question create(Question question) {
        final String sql = "insert into questions (user_id, title, body, created, updated) values (:user_id, :title, :body, :created, :updated);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcClient.sql(sql)
                .param("user_id", question.getUser().getUserId())
                .param("title", question.getTitle())
                .param("body", question.getBody())
                .param("created", question.getCreated())
                .param("updated", question.getUpdated())
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
