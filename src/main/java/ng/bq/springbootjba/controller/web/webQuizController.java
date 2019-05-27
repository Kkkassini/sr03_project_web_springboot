package ng.bq.springbootjba.controller.web;

import java.util.Map;

import javax.validation.Valid;

import ng.bq.springbootjba.Exception.QuizException;
import ng.bq.springbootjba.entity.Question;
import ng.bq.springbootjba.entity.Quiz;
import ng.bq.springbootjba.entity.User;
import ng.bq.springbootjba.service.QuestionService;
import ng.bq.springbootjba.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class webQuizController {

    @Autowired
    QuizService quizService;

    @Autowired
    QuestionService questionService;

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String home() {
//        return "home";
//    }

    @RequestMapping(value = "/createQuiz", method = RequestMethod.GET)
//    @PreAuthorize("isAuthenticated()")ee
    public String newQuiz(Map<String, Object> model) {
        System.out.println("testRequestMapping");

        return "createQuiz";
    }

    @RequestMapping(value = "/createQuiz", method = RequestMethod.POST)
//    @PreAuthorize("isAuthenticated()")
    public String newQuiz(User user, Quiz quiz, BindingResult result,
                          Map<String, Object> model) {
        Quiz newQuiz;
        System.out.println("testRequestMapping");

        try {
//            RestVerifier.verifyModelResult(result);
            newQuiz = quizService.save(quiz, user.getUser());
        } catch (QuizException e) {
            return "createQuiz";
        }

        return "redirect:/editQuiz/" + newQuiz.getId();
    }

    @RequestMapping(value = "/editQuiz/{quiz_id}", method = RequestMethod.GET)
//    @PreAuthorize("isAuthenticated()")
    public ModelAndView editQuiz(@PathVariable long quiz_id) {
        Quiz quiz = quizService.find(quiz_id);

        ModelAndView mav = new ModelAndView();
        mav.addObject("quiz", quiz);
        mav.setViewName("editQuiz");

        return mav;
    }

    @RequestMapping(value = "/editAnswer/{question_id}", method = RequestMethod.GET)
//    @PreAuthorize("isAuthenticated()")
    public ModelAndView editAnswer(@PathVariable long question_id) {
        Question question = questionService.find(question_id);

        ModelAndView mav = new ModelAndView();
        mav.addObject("question", question);
        mav.setViewName("editAnswers");

        return mav;
    }

    @RequestMapping(value = "/quiz/{quiz_id}", method = RequestMethod.GET)
//    @PreAuthorize("permitAll")
    public ModelAndView getQuiz(@PathVariable long quiz_id) {
        Quiz quiz = quizService.find(quiz_id);

        ModelAndView mav = new ModelAndView();
        mav.addObject("quiz", quiz);
        mav.setViewName("quizView");

        return mav;
    }

    @RequestMapping(value = "/quiz/{quiz_id}/play", method = RequestMethod.GET)
//    @PreAuthorize("permitAll")
    public ModelAndView playQuiz(@PathVariable long quiz_id) {
        Quiz quiz = quizService.find(quiz_id);

        ModelAndView mav = new ModelAndView();
        mav.addObject("quiz", quiz);
        mav.setViewName("playQuiz");

        return mav;
    }
}
