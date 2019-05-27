package ng.bq.springbootjba.dao;

import ng.bq.springbootjba.entity.Answer;
import ng.bq.springbootjba.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerDao extends JpaRepository<Answer, Long> {

    int countByQuestion(Question question);

    List<Answer> findByQuestionOrderByOrderAsc(Question question);

}

