package learn.packOverflow.data.mappers;

import learn.packOverflow.models.Question;
import learn.packOverflow.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class QuestionsMapper implements RowMapper<Question> {
    UserMapper userMapper = new UserMapper();
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = userMapper.mapRow(rs, rowNum);

        Question question = new Question();
        question.setQuestionId(rs.getInt("question_id"));
        question.setUser(user);
        question.setTitle(rs.getString("title"));
        question.setBody(rs.getString("body"));
        question.setCreated(rs.getTimestamp("created").toLocalDateTime());
        question.setUpdated(rs.getTimestamp("updated").toLocalDateTime());

        return question;
    }
}
