package com.prepared.capstone.preparedjava.controllers;

import com.prepared.capstone.preparedjava.models.*;
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
@RequestMapping(value="mealplan")
public class MealPlanController {

    @Autowired
    private RecipeDao recipeDao;

    @Autowired
    private MealPlanDao mealPlanDao;

//    @Autowired
//    private RecipeIngredientDao recipeIngredientDao;
//
//    @Autowired
//    private UnitDao unitDao;
//
//    @Autowired
//    private IngredientDao ingredientDao;

    @RequestMapping(value = "all")
    public String index(Model model) {

        model.addAttribute("mealplans", mealPlanDao.findAll());
        model.addAttribute("title", "MealPlans: All");

        return "mealplan/index";
    }

    // View one Recipe
//    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
//    public String viewRecipe(Model model, @PathVariable("id") int id) {
//
//        Recipe recipe =  recipeDao.findOne(id);
//
//        model.addAttribute("title", "View Recipe: " + recipe.getTitle());
//        model.addAttribute("recipeIngredients", recipe.getRecipeIngredients());
//        model.addAttribute("recipeNotes", recipe.getRecipeNotes());
//        model.addAttribute("recipeId", recipe.getId());
//
//        return "mealplan/view";
//    }
//
    // Add Recipe
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddMealPlanForm(Model model) {
        model.addAttribute("title", "Add MealPlan");
        model.addAttribute(new MealPlan());

        return "mealplan/add";
    }
//
//    @RequestMapping(value = "add", method = RequestMethod.POST)
//    public String processAddRecipeForm(@ModelAttribute @Valid Recipe newRecipe,
//                                     Errors errors, Model model) {
//
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Add Recipe");
//            return "mealplan/add";
//        }
//
//        recipeDao.save(newRecipe);
//
//        return "redirect:view/" + newRecipe.getId();
//    }
//
//    // TODO - recipes/edit/{id}/add-recipe
//    @RequestMapping(value = "add-recipe/{id}", method = RequestMethod.GET)
//    public String addIngredient(Model model, @PathVariable("id") int id) {
//
//        Recipe recipe =  recipeDao.findOne(id);
//
//        AddRecipeIngredientForm form = new AddRecipeIngredientForm(unitDao.findAll(), ingredientDao.findAll(), recipe);
//
//        model.addAttribute("title", "Add Ingredient to Recipe: " + recipe.getTitle());
//        model.addAttribute("form", form);
//
//        return "mealplan/add-recipe";
//    }
//
//    @RequestMapping(value = "add-ingredient", method = RequestMethod.POST)
//    public String processAddRecipeIngredientForm( Model model, @ModelAttribute @Valid AddRecipeIngredientForm form,
//                                        Errors errors) {
//
//        if (errors.hasErrors()) {
//            model.addAttribute("form", form);
//            return "mealplan/add-recipe";
//        }
//
//        // create RecipeIngredient
//        Recipe recipe = recipeDao.findOne(form.getRecipeId());
//        Unit theUnit = unitDao.findOne(form.getUnitId());
//        Ingredient theIngredient = ingredientDao.findOne(form.getIngredientId());
//        RecipeIngredient newRecipeIngredient = new RecipeIngredient(
//                form.getAmount(),
//                theUnit,
//                theIngredient,
//                recipe
//        );
//        recipeIngredientDao.save(newRecipeIngredient);
//
//        // save it to Recipe
//        recipe.addIngredient(newRecipeIngredient);
//        recipeDao.save(recipe);
//        return "redirect:/mealplan/view/" + recipe.getId();
//    }



    // TODO - mealplan/delete
    // TODO - mealplan/edit/{id} - edit Title
    // TODO - mealplan/edit/{id}/add-recipe
    // TODO - mealplan/edit/{id}/delete-recipe
    // TODO - mealplan/view/{id}/grocery-list

}
