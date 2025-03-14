package learn.packOverflow.data.mappers;

import learn.packOverflow.models.Answer;
import learn.packOverflow.models.Question;
import learn.packOverflow.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerMapper implements RowMapper<Answer> {

    UserMapper userMapper = new UserMapper();
   QuestionsMapper questionsMapper = new QuestionsMapper();

    @Override
    public Answer mapRow(ResultSet rs, int rowNum) throws SQLException{
        User user = userMapper.mapRow(rs, rowNum);
        Question question = questionsMapper.mapRow(rs, rowNum);


        Answer answer = new Answer();
        answer.setAnswerId(rs.getInt("answer_id"));
        answer.setUser(user);
        answer.setQuestion(question);
        answer.setBody(rs.getString("body"));
        answer.setCreated(rs.getDate("created"));
        answer.setUpdated(rs.getDate("updated"));

        return answer;

    }
}
