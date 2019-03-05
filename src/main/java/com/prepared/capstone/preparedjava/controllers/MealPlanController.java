package com.prepared.capstone.preparedjava.controllers;

import com.prepared.capstone.preparedjava.models.*;
import com.prepared.capstone.preparedjava.models.data.*;
import com.prepared.capstone.preparedjava.models.forms.AddRecipeForm;
import com.prepared.capstone.preparedjava.models.forms.AddRecipeIngredientForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

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
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String viewMealPlan(Model model, @PathVariable("id") int id) {

        MealPlan mealPlan =  mealPlanDao.findOne(id);

        model.addAttribute("title", "View MealPlan: " + mealPlan.getName());
        model.addAttribute("recipes", mealPlan.getRecipes());
        model.addAttribute("mealplanId", mealPlan.getId());

        return "mealplan/view";
    }

    // Add Recipe
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddMealPlanForm(Model model) {
        model.addAttribute("title", "Add MealPlan");
        model.addAttribute("mealplan", new MealPlan());

        return "mealplan/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddMealPlanForm(@ModelAttribute @Valid MealPlan newMealPlan,
                                     Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Meal Plan");
            return "mealplan/add";
        }

        mealPlanDao.save(newMealPlan);

        return "redirect:view/" + newMealPlan.getId();
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String remove(Model model) {
        model.addAttribute("mealplans", mealPlanDao.findAll());
        model.addAttribute("title", "Remove Meal Plan");
        return "mealplan/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String remove(@RequestParam int mealplanId) {

        mealPlanDao.delete(mealplanId);

        return "redirect:/mealplan/all";
    }


    @RequestMapping(value = "add-recipe/{id}", method = RequestMethod.GET)
    public String addRecipeForm(Model model, @PathVariable("id") int id) {

        MealPlan mealPlan =  mealPlanDao.findOne(id);

        AddRecipeForm form = new AddRecipeForm(recipeDao.findAll(), mealPlan);

        model.addAttribute("title", "Add Recipe to MealPlan: " + mealPlan.getName());
        model.addAttribute("form", form);

        return "mealplan/add-recipe";
    }

    @RequestMapping(value = "add-recipe", method = RequestMethod.POST)
    public String processAddRecipeForm( Model model, @ModelAttribute @Valid AddRecipeForm form,
                                        Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "mealplan/add-recipe";
        }


        Recipe aRecipe = recipeDao.findOne(form.getRecipeId());
        MealPlan mealPlan = mealPlanDao.findOne(form.getMealplanId());

        mealPlan.addRecipe(aRecipe);
        mealPlanDao.save(mealPlan);
        return "redirect:/mealplan/view/" + mealPlan.getId();
    }

    @RequestMapping(value = "remove-recipe/{id}", method = RequestMethod.GET)
    public String removeRecipe(Model model, @PathVariable("id") int id) {

        MealPlan mealPlan = mealPlanDao.findOne(id);
        AddRecipeForm form = new AddRecipeForm(mealPlan.getRecipes(), mealPlan);

        model.addAttribute("title", "Remove Recipe from MealPlan: " + mealPlan.getName());
        model.addAttribute("form", form);

        return "mealplan/remove-recipe";
    }

    @RequestMapping(value = "remove-recipe", method = RequestMethod.POST)
    public String processRemoveRecipe(Model model, @ModelAttribute @Valid AddRecipeForm form,
                                      Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "mealplan/remove-recipe";
        }


        Recipe aRecipe = recipeDao.findOne(form.getRecipeId());
        MealPlan mealPlan = mealPlanDao.findOne(form.getMealplanId());

        mealPlan.removeRecipe(aRecipe);
        mealPlanDao.save(mealPlan);
        return "redirect:/mealplan/view/" + mealPlan.getId();
    }


    // TODO - mealplan/edit/{id} - edit Title
    // TODO - mealplan/edit/{id}/add-recipe
    // TODO - mealplan/edit/{id}/delete-recipe
    // TODO - mealplan/view/{id}/grocery-list

    // View one Grocery List
    @RequestMapping(value = "view/{id}/grocery-list", method = RequestMethod.GET)
    public String viewGroceryList(Model model, @PathVariable("id") int id) {

        MealPlan mealPlan =  mealPlanDao.findOne(id);

        //TODO - make list of all ingredients
        ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<>();

        for (Recipe arecipe : mealPlan.getRecipes()) {
            recipeIngredients.addAll(arecipe.getRecipeIngredients());
        }

        model.addAttribute("title", "View MealPlan: " + mealPlan.getName());
        model.addAttribute("recipes", mealPlan.getRecipes());
        model.addAttribute("recipeIngredients", recipeIngredients);
        model.addAttribute("mealplanId", mealPlan.getId());

        return "mealplan/grocery-list";
    }

}
