package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.dao.DiskDAO;
import spring.dao.FilmDAO;
import spring.dao.OrdrDAO;
import spring.forms.FilmForDisk;
import spring.model.DiskEntity;
import spring.model.FilmEntity;
import spring.model.OrdrEntity;
import spring.utils.ErrorRedirect;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class DiskController {

    @Autowired
    DiskDAO diskDAO;
    @Autowired
    FilmDAO filmDAO;
    @Autowired
    OrdrDAO ordrDAO;

    @RequestMapping(value = "/list_disk")
    public ModelAndView diskShow(@RequestParam int all){
        ModelAndView modelAndView = new ModelAndView("list_disk");
        if (all == 1) {
            modelAndView.getModelMap().addAttribute("list", diskDAO.getAll());
        }
        else {
            List<OrdrEntity> active_orders = ordrDAO.getActiveOrdrs();
            List<DiskEntity> disks = diskDAO.getAll();
            for(OrdrEntity o: active_orders){
                disks.remove(o.getDisk());
            }
            modelAndView.getModelMap().addAttribute("list",disks);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/add_disk")
    public ModelAndView addDiskForm(){
        ModelAndView modelAndView = new ModelAndView("add_disk");
        modelAndView.getModelMap().addAttribute("disk", new DiskEntity());
        return modelAndView;
    }

    @RequestMapping(value = "/add_disk_request")
    public ModelAndView addDisk(@ModelAttribute DiskEntity disk){
        if(disk.getType().equals("") || disk.getName().equals("") || disk.getPrice().equals(0))
            return ErrorRedirect.error("empty_field");
        try {
            diskDAO.save(disk);
        } catch (PersistenceException e) {
            return ErrorRedirect.error("database_err");
        }
        return new ModelAndView("redirect:/list_disk?all=1");
    }

    @RequestMapping(value = "/update_disk")
    public ModelAndView updateDiskForm(@RequestParam int diskId){
        ModelAndView modelAndView = new ModelAndView("update_disk");
        modelAndView.getModelMap().addAttribute("disk", diskDAO.getEntityById(diskId));
        return modelAndView;
    }

    @RequestMapping(value = "/update_disk_request")
    public ModelAndView updateDisk(@ModelAttribute DiskEntity diskInput){
        DiskEntity disk = diskDAO.getEntityById(diskInput.getDiskId());
        if(diskInput.getType().equals("") || diskInput.getName().equals("") || diskInput.getPrice().equals(0))
            return ErrorRedirect.error("empty_field");
        List<OrdrEntity> ordrs = ordrDAO.getActiveOrdrs();
        for(OrdrEntity o: ordrs){
            if (o.getDisk().equals(disk)){
                return ErrorRedirect.error("ordered_disk_err");
            }
        }
        disk.setName(diskInput.getName());
        disk.setType(diskInput.getType());
        disk.setPrice(diskInput.getPrice());
        try {
            diskDAO.update(disk);
        } catch (PersistenceException e) {
            return ErrorRedirect.error("database_err");
        }
        return new ModelAndView("redirect:/list_disk?all=1");
    }

    @RequestMapping(value = "/delete_disk")
    public ModelAndView deleteDisk(@RequestParam int diskId){
        DiskEntity disk = diskDAO.getEntityById(diskId);
        try {
            diskDAO.delete(disk);
        }
        catch (PersistenceException e){
            return ErrorRedirect.error("database_err");
        }
        return new ModelAndView("redirect:/list_disk?all=1");
    }

    @RequestMapping(value = "/list_film_for_disk")
    public ModelAndView filmForDiskShow(@RequestParam int diskId){
        ModelAndView modelAndView = new ModelAndView("list_film_for_disk");
        DiskEntity disk = diskDAO.getEntityById(diskId);
        modelAndView.getModelMap().addAttribute("list", disk.getFilms());
        modelAndView.getModelMap().addAttribute("disk", disk);
        return modelAndView;
    }

    @RequestMapping(value = "/add_film_for_disk")
    public ModelAndView addfilmForDiskForm(@RequestParam int diskId){
        ModelAndView modelAndView = new ModelAndView("add_film_for_disk");
        modelAndView.getModelMap().addAttribute("list", filmDAO.getAll());
        FilmForDisk fd = new FilmForDisk();
        fd.setDiskId(diskId);
        modelAndView.getModelMap().addAttribute("film_for_disk", fd);
        DiskEntity disk = diskDAO.getEntityById(diskId);
        modelAndView.getModelMap().addAttribute("disk", disk);
        return modelAndView;
    }

    @RequestMapping(value = "/add_film_for_disk_request")
    public ModelAndView addfilmForDiskForm(@ModelAttribute FilmForDisk fd){
        try {
            FilmEntity film = filmDAO.getEntityById(fd.getFilmId());
            DiskEntity disk = diskDAO.getEntityById(fd.getDiskId());
            List<OrdrEntity> ordrs = ordrDAO.getActiveOrdrs();
            for(OrdrEntity o: ordrs){
                if (o.getDisk().equals(disk)){
                    return ErrorRedirect.error("ordered_disk_err");
                }
            }
            Set<FilmEntity> films = disk.getFilms();
            films.add(film);
            disk.setFilms(films);
            diskDAO.update(disk);
        } catch (PersistenceException e) {
            return ErrorRedirect.error("database_err");
        }
        return new ModelAndView("redirect:/list_film_for_disk?diskId="+fd.getDiskId());
    }

    @RequestMapping(value = "/delete_film_for_disk")
    public ModelAndView deletefilmForDiskForm(@RequestParam int filmId, int diskId){
        try {
            FilmEntity film = filmDAO.getEntityById(filmId);
            DiskEntity disk = diskDAO.getEntityById(diskId);
            List<OrdrEntity> ordrs = ordrDAO.getActiveOrdrs();
            for(OrdrEntity o: ordrs){
                if (o.getDisk().equals(disk)){
                    return ErrorRedirect.error("ordered_disk_err");
                }
            }
            Set<FilmEntity> films = disk.getFilms();
            films.remove(film);
            disk.setFilms(films);
            diskDAO.update(disk);
        } catch (PersistenceException e) {
            return ErrorRedirect.error("database_err");
        }
        return new ModelAndView("redirect:/list_film_for_disk?diskId="+diskId);
    }


}
