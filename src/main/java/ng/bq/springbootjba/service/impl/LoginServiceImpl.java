package ng.bq.springbootjba.service.impl;

import ng.bq.springbootjba.dao.UserDao;
import ng.bq.springbootjba.entity.User;
import ng.bq.springbootjba.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserDao userDao;

    @Override
    public int countAllByMailAndPassword(String mail, String password) {
        return userDao.countAllByMailAndPassword(mail, password);
    }

    @Override
    public User getUserByMailAndPassword(String mail, String password) {
        return userDao.getUserByMailAndPassword(mail, password);
    }

    @Override
    public User getUserByMail(String mail) {
        return userDao.getUserByMail(mail);
    }



}
