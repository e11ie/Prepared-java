package com.prepared.capstone.preparedjava.controllers;

import com.prepared.capstone.preparedjava.models.Ingredient;
import com.prepared.capstone.preparedjava.models.Recipe;
import com.prepared.capstone.preparedjava.models.RecipeIngredient;
import com.prepared.capstone.preparedjava.models.Unit;
import com.prepared.capstone.preparedjava.models.data.*;
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

    @Autowired
    private UnitDao unitDao;

    @Autowired
    private IngredientDao ingredientDao;

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
        model.addAttribute("recipeIngredients", recipe.getRecipeIngredients());
        model.addAttribute("recipeNotes", recipe.getRecipeNotes());
        model.addAttribute("recipeId", recipe.getId());

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

    // TODO - recipes/edit/{id}/add-ingredient
    @RequestMapping(value = "add-ingredient/{id}", method = RequestMethod.GET)
    public String addIngredient(Model model, @PathVariable("id") int id) {

        Recipe recipe =  recipeDao.findOne(id);

        AddRecipeIngredientForm form = new AddRecipeIngredientForm(unitDao.findAll(), ingredientDao.findAll(), recipe);

        model.addAttribute("title", "Add Ingredient to Recipe: " + recipe.getTitle());
        model.addAttribute("form", form);

        return "recipes/add-ingredient";
    }

    @RequestMapping(value = "add-ingredient", method = RequestMethod.POST)
    public String processAddRecipeIngredientForm( Model model, @ModelAttribute @Valid AddRecipeIngredientForm form,
                                        Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "recipes/add-ingredient";
        }

        // create RecipeIngredient
        Recipe recipe = recipeDao.findOne(form.getRecipeId());
        Unit theUnit = unitDao.findOne(form.getUnitId());
        Ingredient theIngredient = ingredientDao.findOne(form.getIngredientId());
        RecipeIngredient newRecipeIngredient = new RecipeIngredient(
                form.getAmount(),
                theUnit,
                theIngredient,
                recipe
        );
        recipeIngredientDao.save(newRecipeIngredient);

        // save it to Recipe
        recipe.addIngredient(newRecipeIngredient);
        recipeDao.save(recipe);
        return "redirect:/recipes/view/" + recipe.getId();
    }



    // TODO - recipes/delete
    // TODO - recipes/edit/{id} - edit Title
    // TODO - recipes/edit/{id}/add-note
    // TODO - recipes/edit/{id}/delete-note
    // TODO - recipes/edit/{id}/delete-ingredient
    // TODO - recipes/edit/{id}/edit-note/{id}
    // TODO - recipes/edit/{id}/edit-ingredient/{id}
}
