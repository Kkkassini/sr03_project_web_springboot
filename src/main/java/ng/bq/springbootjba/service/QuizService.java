package ng.bq.springbootjba.service;

import ng.bq.springbootjba.Exception.ResourceUnavailableException;
import ng.bq.springbootjba.Exception.UnauthorizedActionException;
import ng.bq.springbootjba.Utility.Reponse;
import ng.bq.springbootjba.Utility.Result;
import ng.bq.springbootjba.entity.Quiz;
import ng.bq.springbootjba.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuizService {
	Quiz save(Quiz quiz, User user);

	Page<Quiz> findAll(Pageable pageable);

	Page<Quiz> findAllPublished(Pageable pageable);

	Page<Quiz> findQuizzesByUser(User user, Pageable pageable);

	Quiz find(Long id) throws ResourceUnavailableException;

	Quiz update(Quiz quiz) throws ResourceUnavailableException, UnauthorizedActionException;

	void delete(Quiz quiz) throws ResourceUnavailableException, UnauthorizedActionException;

	Page<Quiz> search(String query, Pageable pageable);

	Result checkAnswers(Quiz quiz, List<Reponse> answersBundle);

	void publishQuiz(Quiz quiz);
}
