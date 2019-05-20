package ng.bq.springbootjba.service.impl;

import ng.bq.springbootjba.dao.UserDao;
import ng.bq.springbootjba.entity.User;
import ng.bq.springbootjba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> findAllByOrderByIdDesc() {
        return userDao.findAllByOrderByIdDesc();
    }
}
