package ng.bq.springbootjba.service.impl;

import ng.bq.springbootjba.Exception.ActionRefusedException;
import ng.bq.springbootjba.Exception.InvalidParamException;
import ng.bq.springbootjba.Exception.ResourceUnavailableException;
import ng.bq.springbootjba.Exception.UnauthorizedActionException;
import ng.bq.springbootjba.Utility.Reponse;
import ng.bq.springbootjba.Utility.Result;
import ng.bq.springbootjba.dao.QuizDao;
import ng.bq.springbootjba.entity.Question;
import ng.bq.springbootjba.entity.Quiz;
import ng.bq.springbootjba.entity.User;
import ng.bq.springbootjba.service.QuestionService;
import ng.bq.springbootjba.service.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("QuizService")
@Transactional
public class QuizServiceImpl implements QuizService {

    private static final Logger logger = LoggerFactory.getLogger(QuizServiceImpl.class);
    private QuizDao quizDao;

    private QuestionService questionService;

    @Autowired
    public QuizServiceImpl(QuizDao quizDao, QuestionService questionService) {
        this.quizDao = quizDao;
        this.questionService = questionService;
    }

    @Override
    public Quiz save(Quiz quiz, User user) {
        quiz.setCreatedBy(user);
        return quizDao.save(quiz);
    }

    @Override
    public Page<Quiz> findAll(Pageable pageable) {
        return quizDao.findAll(pageable);
    }

    @Override
    public Page<Quiz> findAllPublished(Pageable pageable) {
        return quizDao.findByIsPublishedTrue(pageable);
    }

    @Override
    public Quiz find(Long id) throws ResourceUnavailableException {
        Quiz quiz = quizDao.findOne(id);

        if (quiz == null) {
            logger.error("Quiz " + id + " not found");
            throw new ResourceUnavailableException("Quiz " + id + " not found");
        }

        return quiz;
    }

    @Override
    public Quiz update(Quiz newQuiz) throws UnauthorizedActionException, ResourceUnavailableException {
        Quiz currentQuiz = find(newQuiz.getId());

        mergeQuizzes(currentQuiz, newQuiz);
        return quizDao.save(currentQuiz);
    }

    @Override
    public void delete(Quiz quiz) throws ResourceUnavailableException, UnauthorizedActionException {
        quizDao.delete(quiz);
    }

    private void mergeQuizzes(Quiz currentQuiz, Quiz newQuiz) {
        currentQuiz.setName(newQuiz.getName());
        currentQuiz.setDescription(newQuiz.getDescription());
    }

    @Override
    public Page<Quiz> search(String query, Pageable pageable) {
        return quizDao.searchByName(query, pageable);
    }

    @Override
    public Page<Quiz> findQuizzesByUser(User user, Pageable pageable) {
        return quizDao.findByCreatedBy(user, pageable);
    }

    @Override
    public Result checkAnswers(Quiz quiz, List<Reponse> answersBundle) {
        Result results = new Result();

        for (Question question : quiz.getQuestions()) {
            boolean isFound = false;

            if (!question.getIsValid()) {
                continue;
            }

            for (Reponse bundle : answersBundle) {
                if (bundle.getQuestion().equals(question.getId())) {
                    isFound = true;
                    results.addAnswer(questionService.checkIsCorrectAnswer(question, bundle.getSelectedAnswer()));
                    break;
                }
            }

            if (!isFound) {
                throw new InvalidParamException("No answer found for question: " + question.getText());
            }
        }

        return results;
    }

    @Override
    public void publishQuiz(Quiz quiz) {
        int count = questionService.countValidQuestionsInQuiz(quiz);

        if (count > 0) {
            quiz.setIsPublished(true);
            quizDao.save(quiz);
        } else {
            throw new ActionRefusedException("The quiz doesn't have any valid questions");
        }
    }

}

