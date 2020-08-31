package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.dao.ClientDAO;
import spring.model.ClientEntity;
import spring.utils.ErrorRedirect;

import javax.persistence.PersistenceException;

@Controller
public class ClientController {

    @Autowired
    ClientDAO clientDAO;

    @RequestMapping(value = "/list_client")
    public ModelAndView clientShow(){
        ModelAndView modelAndView = new ModelAndView("list_client");
        modelAndView.getModelMap().addAttribute("list", clientDAO.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/add_client")
    public ModelAndView addClientForm(){
        ModelAndView modelAndView = new ModelAndView("add_client");
        modelAndView.getModelMap().addAttribute("client", new ClientEntity());
        return modelAndView;
    }

    @RequestMapping(value = "/add_client_request")
    public ModelAndView addClient(@ModelAttribute ClientEntity client){
        if(client.getName().equals("") || client.getEmail().equals("")
                || client.getPhone().equals(""))
            return ErrorRedirect.error("empty_field");
        try {
            clientDAO.save(client);
        } catch (PersistenceException e) {
            return ErrorRedirect.error("database_err");
        }
        return new ModelAndView("redirect:/list_client");
    }

    @RequestMapping(value = "/update_client")
    public ModelAndView updateClientForm(@RequestParam int clientId){
        ModelAndView modelAndView = new ModelAndView("update_client");
        modelAndView.getModelMap().addAttribute("client", clientDAO.getEntityById(clientId));
        return modelAndView;
    }

    @RequestMapping(value = "/update_client_request")
    public ModelAndView updateClient(@ModelAttribute ClientEntity clientInput){
        ClientEntity client = clientDAO.getEntityById(clientInput.getClientId());
        if(clientInput.getName().equals("") || clientInput.getEmail().equals("")
                || clientInput.getPhone().equals(""))
            return ErrorRedirect.error("empty_field");
        client.setName(clientInput.getName());
        client.setEmail(clientInput.getEmail());
        client.setPhone(clientInput.getPhone());
        try {
            clientDAO.update(client);
        } catch (PersistenceException e) {
            return ErrorRedirect.error("database_err");
        }
        return new ModelAndView("redirect:/list_client");
    }

    @RequestMapping(value = "/delete_client")
    public ModelAndView deleteClient(@RequestParam int clientId){
        ClientEntity client = clientDAO.getEntityById(clientId);
        try {
            clientDAO.delete(client);
        }
        catch (PersistenceException e){
            return ErrorRedirect.error("database_err");
        }
        return new ModelAndView("redirect:/list_client");
    }
}
