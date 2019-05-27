package ng.bq.springbootjba.service.impl;

import ng.bq.springbootjba.Exception.ActionRefusedException;
import ng.bq.springbootjba.Exception.ResourceUnavailableException;
import ng.bq.springbootjba.Exception.UnauthorizedActionException;
import ng.bq.springbootjba.dao.AnswerDao;
import ng.bq.springbootjba.entity.Answer;
import ng.bq.springbootjba.entity.Question;
import ng.bq.springbootjba.service.AnswerService;
import ng.bq.springbootjba.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("AnswerService")
@Transactional
public class AnswerServiceImpl implements AnswerService {

    private static final Logger logger = LoggerFactory.getLogger(AnswerServiceImpl.class);
    private AnswerDao answerDao;

    @Autowired
    @Lazy
    private QuestionService questionService;

    @Autowired
    public AnswerServiceImpl(AnswerDao answerDao) {
        this.answerDao = answerDao;
    }

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Answer find(Long id) throws ResourceUnavailableException {
        Answer answer = answerDao.findOne(id);

        if (answer == null) {
            logger.error("Answer " + id + " not found");
            throw new ResourceUnavailableException("Answer " + id + " not found");
        }

        return answer;
    }

    @Override
    public Answer save(Answer answer) throws UnauthorizedActionException {
        return answerDao.save(answer);
    }

    @Override
    public Answer update(Answer newAnswer) throws ResourceUnavailableException, UnauthorizedActionException {
        Answer currentAnswer = find(newAnswer.getId());

        mergeAnswers(currentAnswer, newAnswer);
        return answerDao.save(currentAnswer);
    }

    @Override
    public void delete(Answer answer) throws ResourceUnavailableException, UnauthorizedActionException {

        if (questionService.checkIsCorrectAnswer(answer.getQuestion(), answer.getId())) {
            throw new ActionRefusedException("The correct answer can't be deleted");
        }

        answerDao.delete(answer);
    }

    private void mergeAnswers(Answer currentAnswer, Answer newAnswer) {
        currentAnswer.setText(newAnswer.getText());

        if (newAnswer.getOrder() != null) {
            currentAnswer.setOrder(newAnswer.getOrder());
        }
    }

    @Override
    public List<Answer> findAnswersByQuestion(Question question) {
        return answerDao.findByQuestionOrderByOrderAsc(question);
    }

    @Override
    public int countAnswersInQuestion(Question question) {
        return answerDao.countByQuestion(question);
    }

}
