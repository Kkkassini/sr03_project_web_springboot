package ng.bq.springbootjba.service;

import ng.bq.springbootjba.entity.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();

    public List<User> findAllByOrderByIdDesc();


}
