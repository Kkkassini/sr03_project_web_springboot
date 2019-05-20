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

    //来到添加页面
    @GetMapping("/user")
    public String toAdd(Model model){
//        List<User> list=userService.findAll();
//        model.addAttribute("users",list);
        return "add";
    }


    //添加操作
    @RequestMapping(value = "/adduser",method = RequestMethod.POST)
    public String insertUser(User user,Map<String,Object> map){
        userDao.save(user);
        map.put("msg","添加成功!!!");
        return "redirect:/ulist";
    }

    //删除
    @DeleteMapping("/ulist/{id}")
    public String delete(User user){
        userDao.delete(user);
        System.out.println("删除数据");
        return  "redirect:/ulist";
    }

    //来到修改页面，查出当前员工，在页面的回显
    @GetMapping("/ulist/{id}")
    public  String toEditPage(@PathVariable("id") Integer id,Model model){
        User user=userDao.getUserById(id);
        model.addAttribute("user",user);

        //回到修改页面（add是一个修改添加二合一的页面）
        return "add";
    }

    //员工修改,需要提交员工id
    @PutMapping("/adduser")
    public String updateUser(User user, Map<String,Object> map){
        userDao.save(user);
        map.put("msg","修改成功");
        System.out.println("修改数据");
        return "redirect:/ulist";

    }



}
