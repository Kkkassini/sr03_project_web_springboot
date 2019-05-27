package ng.bq.springbootjba.controller;

import ng.bq.springbootjba.dao.UserDao;
import ng.bq.springbootjba.entity.User;
import ng.bq.springbootjba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;

    @GetMapping("/user/login")
    public String showIndex(Model model){
        return "index";
    }

    @GetMapping("/ulist")
    public String showPage(Model model){
        List<User> list=userService.findAllByOrderByIdDesc();
        model.addAttribute("user",list);
        return "tables";
    }

    @GetMapping("/user")
    public String toAdd(Model model){
//        List<User> list=userService.findAll();
//        model.addAttribute("users",list);
        return "add";
    }


    @RequestMapping(value = "/adduser",method = RequestMethod.POST)
    public String insertUser(User user,Map<String,Object> map){
        userDao.save(user);
        map.put("msg","User added");
        return "redirect:/ulist";
    }

    @DeleteMapping("/ulist/{id}")
    public String delete(User user){
        userDao.delete(user);
        System.out.println("User deleted");
        return  "redirect:/ulist";
    }

    @GetMapping("/ulist/{id}")
    public  String toEditPage(@PathVariable("id") Integer id,Model model){
        User user=userDao.getUserById(id);
        model.addAttribute("user",user);

        return "add";
    }

    @PutMapping("/adduser")
    public String updateUser(User user, Map<String,Object> map){
        userDao.save(user);
        map.put("msg","User modified");
        System.out.println("User modified");
        return "redirect:/ulist";

    }



}
