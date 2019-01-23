package com.prepared.capstone.preparedjava.controllers;

import com.prepared.capstone.preparedjava.models.data.RecipeDao;
import com.prepared.capstone.preparedjava.models.data.RecipeIngredientDao;
import com.prepared.capstone.preparedjava.models.data.RecipeNoteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="recipes")
public class RecipeController {

    @Autowired
    private RecipeDao recipeDao;

    @Autowired
    private RecipeNoteDao recipeNoteDao;

    @Autowired
    private RecipeIngredientDao recipeIngredientDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("recipes", recipeDao.findAll());
        model.addAttribute("title", "Recipes: All");

        return "recipes/index";
    }

    // TODO - recipes/add
    // TODO - recipes/delete
    // TODO - recipes/{id} - view individual recipe
    // TODO - recipes/edit/{id}
    // TODO - recipes/edit/{id}/add-note
    // TODO - recipes/edit/{id}/add-ingredient
    // TODO - recipes/edit/{id}/delete-note
    // TODO - recipes/edit/{id}/delete-ingredient
    // TODO - recipes/edit/{id}/edit-note/{id}
    // TODO - recipes/edit/{id}/edit-ingredient/{id}
}
