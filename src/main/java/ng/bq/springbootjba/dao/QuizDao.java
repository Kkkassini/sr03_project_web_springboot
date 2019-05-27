package ng.bq.springbootjba.dao;

import ng.bq.springbootjba.entity.Quiz;
import ng.bq.springbootjba.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuizDao extends PagingAndSortingRepository<Quiz, Long> {

    Page<Quiz> findByIsPublishedTrue(Pageable pageable);

    Page<Quiz> findByCreatedBy(User user, Pageable pageable);

    @Query("select q from Quiz q where q.name like %?1%")
    Page<Quiz> searchByName(String name, Pageable pageable);
}
