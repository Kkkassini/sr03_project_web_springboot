package ng.bq.springbootjba.controller;

import ng.bq.springbootjba.dao.UserDao;
import ng.bq.springbootjba.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller

public class LoginController {



    @Autowired
    LoginService loginService;
    @Autowired
    UserDao userDao;

//    @GetMapping(value = "/user/login")

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public String login(@RequestParam("mail") String mail,
                      @RequestParam("password") String password ,
                      Map<String,Object> map, HttpSession session){
        int a =loginService.countAllByMailAndPassword(mail, password);
        //String lastname=userDao.selectLastName(username);
        System.out.println(a);
//        System.out.println(lastname);
        if(a==0){
            map.put("msg","Wrong mail or password");
            return "login";

        }else if(loginService.getUserByMailAndPassword(mail,password).getIs_active()==0){
            map.put("msg","User inactive");
            return "login";
        }else {
            int is_active = loginService.getUserByMailAndPassword(mail, password).getIs_admin();
            session.setAttribute("loginUser",mail);
            session.setAttribute("isAdmin", is_active);
//            session.setAttribute("message",a);
//            return "textajax";
            return "index";
        }



    }


    @RequestMapping("/hello")
    public String hello(){
        return "login";
//        return "textajax";
    }


}
