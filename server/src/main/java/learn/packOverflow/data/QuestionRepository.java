package learn.packOverflow.data;

import learn.packOverflow.models.Question;

import java.util.List;

public interface QuestionRepository {
    List<Question> findAll();
    List<Question> findByUser(int userId);
    List<Question> findByKeyword(String keyword);
    Question create(Question question);
    boolean update(Question question);
    boolean deleteById(int id);
}
