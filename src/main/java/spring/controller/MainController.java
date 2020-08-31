package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.dao.*;
import spring.model.ClientEntity;
import spring.utils.ErrorRedirect;

import javax.persistence.PersistenceException;

@Controller
public class MainController {

    @Autowired
    OrdrDAO ordrDAO;

    @RequestMapping(value = "/")
    public ModelAndView main(){
        ErrorRedirect.errorInitialize();
        ModelAndView modelAndView =  new ModelAndView("index");
        modelAndView.getModelMap().addAttribute("list", ordrDAO.getAll());
        return modelAndView;
    }

}

