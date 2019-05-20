package ng.bq.springbootjba.service;

import ng.bq.springbootjba.entity.User;

public interface LoginService {
    int countAllByMailAndPassword(String mail, String password);
    User getUserByMailAndPassword(String mail, String password);
    User getUserByMail(String mail);


}
