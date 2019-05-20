package ng.bq.springbootjba.dao;

import ng.bq.springbootjba.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

        import java.util.List;

public interface QuestionDao extends JpaRepository<Question, Long> {

    int countAllByTitle(String title);
    Question getQuestionByTitle(String title);

    List<Question> findAll();
    List<Question> findAllByOrderByIdDesc();
    Question getQuestionById(Integer id);



}
