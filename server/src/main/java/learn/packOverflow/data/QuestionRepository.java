package learn.packOverflow.data;

import learn.packOverflow.models.Question;

import java.util.List;

public interface QuestionRepository {
    List<Question> findAll();
    List<Question> findByUser();
    List<Question> findByKeyword();
    Question create(Question question);
    boolean update(Question question);
    boolean deleteById(int id);
}
