package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.dao.ClientDAO;
import spring.dao.DiskDAO;
import spring.dao.OrdrDAO;
import spring.forms.OrdrAddForm;
import spring.forms.OrdrUpdateForm;
import spring.model.DiskEntity;
import spring.model.OrdrEntity;
import spring.utils.Converter;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrdrController {

    @Autowired
    OrdrDAO ordrDAO;
    @Autowired
    ClientDAO clientDAO;
    @Autowired
    DiskDAO diskDAO;

    @RequestMapping(value = "/add_ordr")
    public ModelAndView addDiskForm(){
        ModelAndView modelAndView = new ModelAndView("add_ordr");
        modelAndView.getModelMap().addAttribute("ordr_form", new OrdrAddForm());
        modelAndView.getModelMap().addAttribute("client_list", clientDAO.getAll());
        List<OrdrEntity> active_orders = ordrDAO.getActiveOrdrs();
        List<DiskEntity> disks = diskDAO.getAll();
        for(OrdrEntity o: active_orders){
            disks.remove(o.getDisk());
        }
        modelAndView.getModelMap().addAttribute("disk_list", disks);
        return modelAndView;
    }

    @RequestMapping(value = "/add_ordr_request")
    public ModelAndView addDisk(@ModelAttribute OrdrAddForm ordrForm){
        if(ordrForm.getRequestTime().equals("") || ordrForm.getReturnTime().equals(""))
            return new ModelAndView("redirect:/");
        try {
            OrdrEntity ordr = new OrdrEntity();
            ordr.setClient(clientDAO.getEntityById(ordrForm.getClientId()));
            ordr.setDisk(diskDAO.getEntityById(ordrForm.getDiskId()));
            ordr.setIsReturned(Boolean.FALSE);
            try {
                ordr.setRequestTime(Converter.strToDate(ordrForm.getRequestTime()));
                ordr.setReturnTime(Converter.strToDate(ordrForm.getReturnTime()));
            }
            catch (Exception e) {
                return new ModelAndView("redirect:/");
            }
            if (ordr.getReturnTime().compareTo(ordr.getRequestTime()) <= 0){
                return new ModelAndView("redirect:/");
            }
            ordrDAO.save(ordr);
        } catch (PersistenceException e) {
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/return_request")
    public ModelAndView returnRequest(@RequestParam int ordrId){
        OrdrEntity ordr = ordrDAO.getEntityById(ordrId);
        ordr.setIsReturned(Boolean.TRUE);
        ordrDAO.update(ordr);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/update_ordr")
    public ModelAndView updateOrdrForm(@RequestParam int ordrId){
        ModelAndView modelAndView = new ModelAndView("update_ordr");
        OrdrEntity ordr = ordrDAO.getEntityById(ordrId);
        modelAndView.getModelMap().addAttribute("ordr", ordr);
        List<OrdrEntity> active_orders = ordrDAO.getActiveOrdrs();
        List<DiskEntity> disks = diskDAO.getAll();
        for(OrdrEntity o: active_orders){
            disks.remove(o.getDisk());
        }
        modelAndView.getModelMap().addAttribute("disk_list", disks);
        modelAndView.getModelMap().addAttribute("client_list", clientDAO.getAll());
        OrdrUpdateForm ordrForm = new OrdrUpdateForm();
        ordrForm.setOrdrId(ordrId);
        ordrForm.setRequestTime(Converter.dateToStr(ordr.getRequestTime()));
        ordrForm.setReturnTime(Converter.dateToStr(ordr.getReturnTime()));
        modelAndView.getModelMap().addAttribute("ordr_form", ordrForm);
        return modelAndView;
    }

    @RequestMapping(value = "/update_ordr_request")
    public ModelAndView addDisk(@ModelAttribute OrdrUpdateForm ordrForm) {
        try {
            OrdrEntity ordr = ordrDAO.getEntityById(ordrForm.getOrdrId());
            ordr.setClient(clientDAO.getEntityById(ordrForm.getClientId()));
            ordr.setDisk(diskDAO.getEntityById(ordrForm.getDiskId()));
            try {
                ordr.setRequestTime(Converter.strToDate(ordrForm.getRequestTime()));
                ordr.setReturnTime(Converter.strToDate(ordrForm.getReturnTime()));
            }
            catch (Exception e) {
                return new ModelAndView("redirect:/");
            }
            if (ordr.getReturnTime().compareTo(ordr.getRequestTime()) <= 0){
                return new ModelAndView("redirect:/");
            }
            ordrDAO.update(ordr);
        } catch (PersistenceException e) {
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/delete_ordr")
    public ModelAndView deleteDisk(@RequestParam int ordrId){
        OrdrEntity ordr = ordrDAO.getEntityById(ordrId);
        ordrDAO.delete(ordr);
        return new ModelAndView("redirect:/");
    }

}
