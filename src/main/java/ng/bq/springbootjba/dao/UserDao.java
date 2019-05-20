package ng.bq.springbootjba.dao;

import ng.bq.springbootjba.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {

//    @Query(value = "select count(user_name) from tbl_user where user_name= ?1 and password= ?2 ")
    int countAllByMailAndPassword(String mail, String password);
    User getUserByMailAndPassword(String mail, String password);
    User getUserByMail(String mail);
//    @Query(value = "select last_name from tbl_user where user_name=?",nativeQuery = true)
//    String selectLastName(String username);

    List<User> findAll();
    List<User> findAllByOrderByIdDesc();
    User getUserById(Integer id);



}
