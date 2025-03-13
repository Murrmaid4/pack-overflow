package learn.packOverflow.data;

import learn.packOverflow.models.Answer;

import java.util.List;

public interface AnswerRepository {

    public List<Answer> findByQuestionId(int questionId);
    public Answer create(Answer answer);
    public boolean update(Answer answer);
    public boolean deleteById(int id);



}
