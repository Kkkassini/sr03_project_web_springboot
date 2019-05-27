package ng.bq.springbootjba.dao;

import ng.bq.springbootjba.entity.Question;
import ng.bq.springbootjba.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question, Long> {

    int countByQuiz(Quiz quiz);

    int countByQuizAndIsValidTrue(Quiz quiz);

    List<Question> findByQuizOrderByOrderAsc(Quiz quiz);

    List<Question> findByQuizAndIsValidTrueOrderByOrderAsc(Quiz quiz);
}
