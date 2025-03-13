package learn.packOverflow.data.mappers;

import learn.packOverflow.models.Question;
import learn.packOverflow.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class QuestionsMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt());


        Question question = new Question();
        question.setQuestionId(rs.getInt("question_id"));
//        question.setUser()
        question.setTitle(rs.getString("title"));
        question.setBody(rs.getString("body"));
        question.setCreated(rs.getDate("created").toLocalDate());
        question.setUpdated(rs.getDate("updated").toLocalDate());

        return question;
    }
}
