package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.utils.ErrorRedirect;

@Controller
public class ErrorController {

    @RequestMapping(value = "/error")
    public ModelAndView error(@RequestParam String msg_id){
        ModelAndView modelAndView =  new ModelAndView("error");
        modelAndView.getModelMap().addAttribute("msg", ErrorRedirect.getError(msg_id));
        return modelAndView;
    }

}
