package ng.bq.springbootjba.repository;

import ng.bq.springbootjba.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
