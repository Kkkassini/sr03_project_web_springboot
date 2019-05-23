package ng.bq.springbootjba.service;

import ng.bq.springbootjba.entity.Question;

import java.util.List;

public interface QuestionService {

    public List<Question> findAll();

    public List<Question> findAllByOrderByIdDesc();


}
