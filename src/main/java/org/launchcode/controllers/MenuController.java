package org.launchcode.controllers;

import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by AnnaYoungyeun on 6/22/17.
 */

@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;

    // index handler
    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("title", "Menus");
        model.addAttribute("menu", menuDao.findAll());
        return "menu/index";
    }

    // add menus
    @RequestMapping(value="add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute(new Menu());
        return "menu/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Menu menu, Errors errors){

        if (errors.hasErrors()){
            model.addAttribute("title", "Add Menu");
            return "menu/add";
        }

        menuDao.save(menu);
        return "redirect:view/" + menu.getId();
    }

    // view handler
    @RequestMapping(value = "view/{menuId}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int menuId){
        Menu m = menuDao.findOne(menuId);
        model.addAttribute("title", m.getName());
        model.addAttribute("cheeses", menuDao.findAll());

        return "view";
    }

    //add items
    @RequestMapping(value = "add-item/{menuId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int menuId){
        Menu m = menuDao.findOne(menuId);
        model.addAttribute("title", "Add item to menu:" + m.getName());
        model.addAttribute( "form", new AddMenuItemForm(m, cheeseDao.findAll()));
        return "add-item";

    }
    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddMenuItemForm addMenuItemForm, Errors errors){

        if(errors.hasErrors()){
            return "menu/add-item";
        }

        int cheeseId = addMenuItemForm.getCheeseId();
        int menuId = addMenuItemForm.getMenuId();

        Cheese c = cheeseDao.findOne(cheeseId);
        Menu m = menuDao.findOne(menuId);

        m.addItem(c);
        menuDao.save(m);

        return "view/{menuId}";
    }

}
