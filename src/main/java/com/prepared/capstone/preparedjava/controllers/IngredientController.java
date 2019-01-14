package com.prepared.capstone.preparedjava.controllers;


import com.prepared.capstone.preparedjava.models.Ingredient;
import com.prepared.capstone.preparedjava.models.IngredientCategory;
import com.prepared.capstone.preparedjava.models.data.IngredientCategoryDao;
import com.prepared.capstone.preparedjava.models.data.IngredientDao;
import com.prepared.capstone.preparedjava.models.forms.AddIngredientForm;
import com.prepared.capstone.preparedjava.models.forms.EditIngredientForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value="ingredient")
public class IngredientController {

    @Autowired
    private IngredientDao ingredientDao;

    @Autowired
    private IngredientCategoryDao ingredientCategoryDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("ingredients", ingredientDao.findAll());
        model.addAttribute("title", "Ingredients");

        return "ingredients/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {

        AddIngredientForm form = new AddIngredientForm(ingredientCategoryDao.findAll());

        model.addAttribute("title", "Add Ingredient");
        model.addAttribute("form", form);
        return "ingredients/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add (Model model,
                       @ModelAttribute @Valid AddIngredientForm form, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "ingredients/add";
        }

        Ingredient newIngredient = new Ingredient(form.getName());
        newIngredient.setCategory(ingredientCategoryDao.findOne(form.getCategoryId()));
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

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") int id) {

        Ingredient ingredient = ingredientDao.findOne(id);
        Iterable<IngredientCategory> categories = ingredientCategoryDao.findAll();

        EditIngredientForm form = new EditIngredientForm(categories, ingredient);
        form.setName(ingredient.getName());
        form.setCategoryId(ingredient.getCategory().getId());

        model.addAttribute("title", "Edit Ingredient: " + ingredient.getName());
        model.addAttribute("form", form);

        return "ingredients/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String editIngredient( Model model, @ModelAttribute @Valid EditIngredientForm form,
                                Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "ingredients/edit";
        }

        Ingredient ingredient = ingredientDao.findOne(form.getIngredientId());
        ingredient.setName(form.getName());
        ingredient.setCategory(ingredientCategoryDao.findOne(form.getCategoryId()));
        ingredientDao.save(ingredient);
        return "redirect:/ingredient";
    }
}
