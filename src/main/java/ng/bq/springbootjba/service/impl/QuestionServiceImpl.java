package ng.bq.springbootjba.service.impl;


import ng.bq.springbootjba.Exception.ActionRefusedException;
import ng.bq.springbootjba.Exception.ResourceUnavailableException;
import ng.bq.springbootjba.Exception.UnauthorizedActionException;
import ng.bq.springbootjba.dao.QuestionDao;
import ng.bq.springbootjba.entity.Answer;
import ng.bq.springbootjba.entity.Question;
import ng.bq.springbootjba.entity.Quiz;
import ng.bq.springbootjba.service.AnswerService;
import ng.bq.springbootjba.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("QuestionService")
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);
    private QuestionDao questionDao;

    private AnswerService answerService;

    @Autowired
    public QuestionServiceImpl(QuestionDao questionDao, AnswerService answerService) {
        this.questionDao = questionDao;
        this.answerService = answerService;
    }

    @Override
    public Question save(Question question) throws UnauthorizedActionException {
        int count = questionDao.countByQuiz(question.getQuiz());
        question.setOrder(count + 1);

        return questionDao.save(question);
    }

    @Override
    public Question find(Long id) throws ResourceUnavailableException {
        Question question = questionDao.findOne(id);

        if (question == null) {
            logger.error("Question " + id + " not found");
            throw new ResourceUnavailableException("Question " + id + " not found");
        }

        return question;
    }

    @Override
    public Question update(Question newQuestion) throws ResourceUnavailableException, UnauthorizedActionException {
        Question currentQuestion = find(newQuestion.getId());

        mergeQuestions(currentQuestion, newQuestion);
        return questionDao.save(currentQuestion);
    }

    @Override
    public void delete(Question question) throws ResourceUnavailableException, UnauthorizedActionException {
        Quiz quiz = question.getQuiz();

        if (quiz.getIsPublished() && question.getIsValid() && countValidQuestionsInQuiz(quiz) <= 1) {
            throw new ActionRefusedException("A Quiz can't contain less than one valid question");
        }

        questionDao.delete(question);
    }

    private void mergeQuestions(Question currentQuestion, Question newQuestion) {
        currentQuestion.setText(newQuestion.getText());

        if (newQuestion.getOrder() != null)
            currentQuestion.setOrder(newQuestion.getOrder());
    }

    @Override
    public Boolean checkIsCorrectAnswer(Question question, Long answer_id) {
        if (!question.getIsValid() || question.getCorrectAnswer() == null) {
            return false;
        }

        return question.getCorrectAnswer().getId().equals(answer_id);
    }

    @Override
    public List<Question> findQuestionsByQuiz(Quiz quiz) {
        return questionDao.findByQuizOrderByOrderAsc(quiz);
    }

    @Override
    public List<Question> findValidQuestionsByQuiz(Quiz quiz) {
        return questionDao.findByQuizAndIsValidTrueOrderByOrderAsc(quiz);
    }

    @Override
    public void setCorrectAnswer(Question question, Answer answer) {
        question.setCorrectAnswer(answer);
        save(question);
    }

    @Override
    public Answer addAnswerToQuestion(Answer answer, Question question) {
        int count = answerService.countAnswersInQuestion(question);
        Answer newAnswer = updateAndSaveAnswer(answer, question, count);

        checkQuestionInitialization(question, count, newAnswer);

        return newAnswer;
    }

    private void checkQuestionInitialization(Question question, int count, Answer newAnswer) {
        checkAndUpdateQuestionValidity(question, true);
        setCorrectAnswerIfFirst(question, count, newAnswer);
    }

    private Answer updateAndSaveAnswer(Answer answer, Question question, int count) {
        answer.setOrder(count + 1);
        answer.setQuestion(question);
        return answerService.save(answer);
    }

    private void checkAndUpdateQuestionValidity(Question question, boolean newState) {
        if (!question.getIsValid()) {
            question.setIsValid(newState);
            save(question);
        }
    }

    private void setCorrectAnswerIfFirst(Question question, int count, Answer newAnswer) {
        if (count == 0) {
            question.setCorrectAnswer(newAnswer);
            questionDao.save(question);
        }
    }

    @Override
    public int countQuestionsInQuiz(Quiz quiz) {
        return questionDao.countByQuiz(quiz);
    }

    @Override
    public int countValidQuestionsInQuiz(Quiz quiz) {
        return questionDao.countByQuizAndIsValidTrue(quiz);
    }

    @Override
    public Answer getCorrectAnswer(Question question) {
        return question.getCorrectAnswer();
    }

}
