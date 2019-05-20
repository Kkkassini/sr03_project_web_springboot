package ng.bq.springbootjba.service.impl;

import ng.bq.springbootjba.dao.QuestionDao;
import ng.bq.springbootjba.dao.UserDao;
import ng.bq.springbootjba.entity.Question;
import ng.bq.springbootjba.entity.User;
import ng.bq.springbootjba.service.QuestionService;
import ng.bq.springbootjba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionDao questionDao;

    @Override
    public List<Question> findAll() {
        return questionDao.findAll();
    }

    @Override
    public List<Question> findAllByOrderByIdDesc() {
        return questionDao.findAllByOrderByIdDesc();
    }
}
