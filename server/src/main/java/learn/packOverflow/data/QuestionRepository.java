package learn.packOverflow.data;

import learn.packOverflow.models.Question;

import java.util.List;
import java.util.Set;

public interface QuestionRepository {
    List<Question> findAll();
    List<Question> findByUser(int userId);
    Set<Question> findByKeyword(String keyword);
    Question findById(int questionId);
    Question create(Question question);
    boolean update(Question question);
    boolean deleteById(int id);
}
