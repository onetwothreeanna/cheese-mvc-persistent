package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by AnnaYoungyeun on 6/19/17.
 */
@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao; //Field of type CategoryDao.  Mechanism with which we interact with objects stored in the database


    //  index handler
    @RequestMapping(value = "") //root
    public String index(Model model) { //use that model to pass data to the view(template)

        model.addAttribute("category", categoryDao.findAll()); //returns an Iterable of all Category Objects managed by categoryDao
        model.addAttribute("title", "Categories");
        return "category/index";
    }

    //  add handlers
    @RequestMapping(value="add", method= RequestMethod.GET)
    public String addHandler(Model model){
        model.addAttribute("title", "Add Category");
        model.addAttribute(new Category()); //create new Category object and pass to view.  Same as ("category", new Category())
        return "category/add";
    }

    @RequestMapping(value="add", method= RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Category category, Errors errors){

        if (errors.hasErrors()){
            model.addAttribute("title", "Add Category");
            return "category/add";
        }

        categoryDao.save(category);
        return "redirect:";
    }
}
