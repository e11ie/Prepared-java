package com.prepared.capstone.preparedjava.controllers;


import com.prepared.capstone.preparedjava.models.Ingredient;
import com.prepared.capstone.preparedjava.models.data.IngredientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping(value="ingredient")
public class IngredientController {

    @Autowired
    private IngredientDao ingredientDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("ingredients", ingredientDao.findAll());
        model.addAttribute("title", "Ingredients");

        return "ingredients/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add Category");
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add (Model model,
                       @ModelAttribute @Valid Ingredient newIngredient, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Ingredient");
            return "ingredients/add";
        }

        ingredientDao.save(newIngredient);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String remove(Model model) {
        model.addAttribute("ingredients", ingredientDao.findAll());
        model.addAttribute("title", "Remove Ingredient");
        return "ingredients/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String remove(@RequestParam int ingredientId) {

        ingredientDao.delete(ingredientId);

        return "redirect:";
    }
}
