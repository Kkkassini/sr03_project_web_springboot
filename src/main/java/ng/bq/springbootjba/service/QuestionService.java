package ng.bq.springbootjba.service;

import ng.bq.springbootjba.entity.Answer;
import ng.bq.springbootjba.entity.Question;
import ng.bq.springbootjba.entity.Quiz;

import java.util.List;

public interface QuestionService {
    Question save(Question question) ;

    Question find(Long id) ;

    List<Question> findQuestionsByQuiz(Quiz quiz);

    List<Question> findValidQuestionsByQuiz(Quiz quiz);

    Question update(Question question) ;

    void delete(Question question) ;

    Boolean checkIsCorrectAnswer(Question question, Long answer_id);

    void setCorrectAnswer(Question question, Answer answer);

    Answer getCorrectAnswer(Question question);

    Answer addAnswerToQuestion(Answer answer, Question question);

    int countQuestionsInQuiz(Quiz quiz);

    int countValidQuestionsInQuiz(Quiz quiz);
}
