package ng.bq.springbootjba.service;

import ng.bq.springbootjba.entity.Answer;
import ng.bq.springbootjba.entity.Question;

import java.util.List;

public interface AnswerService {
    Answer save(Answer answer) ;

    Answer find(Long id) ;

    Answer update(Answer newAnswer) ;

    void delete(Answer answer) ;

    List<Answer> findAnswersByQuestion(Question question);

    int countAnswersInQuestion(Question question);
}
