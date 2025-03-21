package learn.packOverflow.domain;

import learn.packOverflow.data.QuestionRepository;
import learn.packOverflow.models.Question;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class QuestionService {
    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }
    public List<Question> findAll() {
        return repository.findAll();
    }
    Result<List<Question>> findByUser(int userId) {
        Result<List<Question>> result = new Result<>();
        if (userId <= 0){
            result.addErrorMessage("Invalid user ID", ResultType.INVALID);
            return result;
        }
        List<Question> questionsByUser = repository.findByUser(userId);
        if (questionsByUser.isEmpty()){
            result.addErrorMessage("No questions found", ResultType.NOT_FOUND);
        } else {
            result.setPayload(questionsByUser);
        }
        return result;
    }
    Result<Set<Question>> findByKeyword(String keyword) {
        Result<Set<Question>> result = new Result<>();
        if (keyword == null || keyword.isBlank()){
            result.addErrorMessage("keyword required", ResultType.INVALID);
            return result;
        }
        Set<Question> questionsByKeyword = repository.findByKeyword(keyword);
        if (questionsByKeyword == null || questionsByKeyword.isEmpty()){
            result.addErrorMessage("No questions found", ResultType.NOT_FOUND);
        } else {
            result.setPayload(questionsByKeyword);
        }
        return result;
    }
    Result<Question> findById(int questionId) {
        Result<Question> result = new Result<>();
        if (questionId <= 0){
            result.addErrorMessage("Invalid question ID", ResultType.INVALID);
            return result;
        }
        Question foundQuestion = repository.findById(questionId);
        if (foundQuestion == null){
            result.addErrorMessage("Question not found", ResultType.NOT_FOUND);
        } else {
            result.setPayload(foundQuestion);
        }
        return result;
    }
    Result<Question> create(Question questionBeforeAdd) {
        Result<Question> result = validateFields(questionBeforeAdd);
        Question questionAfterAdd = repository.create(questionBeforeAdd);
        result.setPayload(questionAfterAdd);

        return null;
    }
    Result<Question> update(Question question) {
        Result<Question> result = new Result<>();
        return null;
    }
    Result<Boolean> deleteById(int id) {
        return null;
    }
    private Result<Question> validateFields(Question question){
        Result<Question> result = new Result<>();
        if (question == null){
            result.addErrorMessage("Question cannot be null", ResultType.INVALID);
        }
        if (question.getUser() == null){
            result.addErrorMessage("User cannot be null", ResultType.INVALID);
        }
        if (question.getTitle() == null || question.getTitle().isBlank()){
            result.addErrorMessage("Title is required", ResultType.INVALID);
        }
        if (question.getBody() == null || question.getBody().isBlank()){
            result.addErrorMessage("Body is required", ResultType.INVALID);
        }
        return result;
    }
}
