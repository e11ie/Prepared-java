package com.prepared.capstone.preparedjava.controllers;

import com.prepared.capstone.preparedjava.models.IngredientCategory;
import com.prepared.capstone.preparedjava.models.data.IngredientCategoryDao;
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
@RequestMapping(value="ingredient/category")
public class IngredientCategoryController {

    @Autowired
    private IngredientCategoryDao categoryDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "Ingredient Categories");

        return "ingredient-category/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add Category");
        model.addAttribute("category", new IngredientCategory());
        return "ingredient-category/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add (Model model,
                                @ModelAttribute @Valid IngredientCategory newCategory, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Category");
            return "ingredient-category/add";
        }

        categoryDao.save(newCategory);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String remove(Model model) {
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "Remove Category");
        return "ingredient-category/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String remove(@RequestParam int categoryId) {

        categoryDao.delete(categoryId);

        return "redirect:";
    }
}
