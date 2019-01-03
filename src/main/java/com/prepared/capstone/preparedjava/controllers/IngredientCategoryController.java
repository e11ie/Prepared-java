package com.prepared.capstone.preparedjava.controllers;

import com.prepared.capstone.preparedjava.models.data.IngredientCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="category")
public class IngredientCategoryController {

    @Autowired
    private IngredientCategoryDao categoryDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "Ingredient Categories");

        return "ingredient-category/index";
    }
}
