package com.prepared.capstone.preparedjava.controllers;

import com.prepared.capstone.preparedjava.models.Recipe;
import com.prepared.capstone.preparedjava.models.data.RecipeDao;
import com.prepared.capstone.preparedjava.models.data.RecipeIngredientDao;
import com.prepared.capstone.preparedjava.models.data.RecipeNoteDao;
import com.prepared.capstone.preparedjava.models.forms.AddRecipeIngredientForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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

    // View one Recipe
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String viewRecipe(Model model, @PathVariable("id") int id) {

        Recipe recipe =  recipeDao.findOne(id);

        model.addAttribute("title", "View Recipe: " + recipe.getTitle());
//        model.addAttribute("cheeses", menu.getCheeses());
//        model.addAttribute("menuId", menu.getId());

        return "recipes/view";
    }

    // Add Recipe
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayRecipeForm(Model model) {
        model.addAttribute("title", "Add Recipe");
        model.addAttribute(new Recipe());

        return "recipes/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddRecipeForm(@ModelAttribute @Valid Recipe newRecipe,
                                     Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Recipe");
            return "recipes/add";
        }

        recipeDao.save(newRecipe);

        return "redirect:view/" + newRecipe.getId();
    }



    // TODO - recipes/delete
    // TODO - recipes/edit/{id} - edit Title
    // TODO - recipes/edit/{id}/add-note
    // TODO - recipes/edit/{id}/add-ingredient
    // TODO - recipes/edit/{id}/delete-note
    // TODO - recipes/edit/{id}/delete-ingredient
    // TODO - recipes/edit/{id}/edit-note/{id}
    // TODO - recipes/edit/{id}/edit-ingredient/{id}
}
