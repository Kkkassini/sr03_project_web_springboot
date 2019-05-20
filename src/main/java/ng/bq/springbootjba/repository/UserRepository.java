package ng.bq.springbootjba.repository;


import ng.bq.springbootjba.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

//    @Query(value = "select count (user_name) from tbl_user where user_name=?1  and password= ?2")
//    int  findNameandpassword( String username, String password);

}
