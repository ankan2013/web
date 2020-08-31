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
public class FilmController {

    @Autowired
    FilmDAO filmDAO;
    @Autowired
    DiskDAO diskDAO;
    @Autowired
    OrdrDAO ordrDAO;
    
    @RequestMapping(value = "/list_film")
    public ModelAndView filmShow(){
        ModelAndView modelAndView = new ModelAndView("list_film");
        modelAndView.getModelMap().addAttribute("list", filmDAO.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/list_disk_for_film")
    public ModelAndView filmForDiskShow(@RequestParam int filmId, int all){
        ModelAndView modelAndView = new ModelAndView("list_disk_for_film");
        FilmEntity film = filmDAO.getEntityById(filmId);
        if (all == 1) {
            modelAndView.getModelMap().addAttribute("list", film.getDisks());
        }
        else {
            List<OrdrEntity> active_orders = ordrDAO.getActiveOrdrs();
            List<DiskEntity> disks = new ArrayList<>(film.getDisks());
            for(OrdrEntity o: active_orders){
                disks.remove(o.getDisk());
            }
            modelAndView.getModelMap().addAttribute("list", disks);
        }
        modelAndView.getModelMap().addAttribute("film", film);
        return modelAndView;
    }


    @RequestMapping(value = "/add_film")
    public ModelAndView addfilmForm(){
        ModelAndView modelAndView = new ModelAndView("add_film");
        modelAndView.getModelMap().addAttribute("film", new FilmEntity());
        return modelAndView;
    }

    @RequestMapping(value = "/add_film_request")
    public ModelAndView addFilm(@ModelAttribute FilmEntity film){
        if(film.getName().equals("")
                || film.getInfo().equals(""))
            return ErrorRedirect.error("empty_field");
        try {
            filmDAO.save(film);
        } catch (PersistenceException e) {
            return ErrorRedirect.error("database_err");
        }
        return new ModelAndView("redirect:/list_film");
    }

    @RequestMapping(value = "/update_film")
    public ModelAndView updateFilmForm(@RequestParam int filmId){
        ModelAndView modelAndView = new ModelAndView("update_film");
        modelAndView.getModelMap().addAttribute("film", filmDAO.getEntityById(filmId));
        return modelAndView;
    }

    @RequestMapping(value = "/update_film_request")
    public ModelAndView updateFilm(@ModelAttribute FilmEntity filmInput){
        FilmEntity film = filmDAO.getEntityById(filmInput.getFilmId());
        if(filmInput.getName().equals("")
                || filmInput.getInfo().equals(""))
            return ErrorRedirect.error("empty_field");
        film.setName(filmInput.getName());
        film.setName(filmInput.getName());
        film.setInfo(filmInput.getInfo());
        try {
            filmDAO.update(film);
        } catch (PersistenceException e) {
            return ErrorRedirect.error("database_err");
        }
        return new ModelAndView("redirect:/list_film");
    }

    @RequestMapping(value = "/delete_film")
    public ModelAndView deleteFilm(@RequestParam int filmId){
        FilmEntity film = filmDAO.getEntityById(filmId);
        try {
            filmDAO.delete(film);
        }
        catch (PersistenceException e){
            return ErrorRedirect.error("database_err");
        }
        return new ModelAndView("redirect:/list_film");
    }
}
