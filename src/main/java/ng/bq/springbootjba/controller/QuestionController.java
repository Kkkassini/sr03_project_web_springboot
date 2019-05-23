package ng.bq.springbootjba.controller;

import ng.bq.springbootjba.dao.QuestionDao;
import ng.bq.springbootjba.entity.Question;
import ng.bq.springbootjba.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionDao questionDao;

    @GetMapping("/q")
    public String showIndex(Model model){
        return "index";
    }

    @GetMapping("/qlist")
    public String showPage(Model model){
        List<Question> list=questionService.findAllByOrderByIdDesc();
        model.addAttribute("question",list);
        return "question";
    }

    //来到添加页面
    @GetMapping("/question")
    public String toAdd(Model model){
//        List<Question> list=questionService.findAll();
//        model.addAttribute("questions",list);
        return "add";
    }


    //添加操作
    @RequestMapping(value = "/addquestion",method = RequestMethod.POST)
    public String insertQuestion(Question question,Map<String,Object> map){
        questionDao.save(question);
        map.put("msg","添加成功!!!");
        return "redirect:/qlist";
    }

    //删除
    @DeleteMapping("/qlist/{id}")
    public String delete(Question question){
        questionDao.delete(question);
        System.out.println("删除数据");
        return  "redirect:/qlist";
    }

    //来到修改页面，查出当前员工，在页面的回显
    @GetMapping("/qlist/{id}")
    public  String toEditPage(@PathVariable("id") Integer id,Model model){
        Question question=questionDao.getQuestionById(id);
        model.addAttribute("question",question);

        //回到修改页面（add是一个修改添加二合一的页面）
        return "add";
    }

    //员工修改,需要提交员工id
    @PutMapping("/addquestion")
    public String updateQuestion(Question question, Map<String,Object> map){
        questionDao.save(question);
        map.put("msg","修改成功");
        System.out.println("修改数据");
        return "redirect:/qlist";

    }



}
