package com.prepared.capstone.preparedjava.models.forms;

import com.prepared.capstone.preparedjava.models.Ingredient;
import com.prepared.capstone.preparedjava.models.MealPlan;
import com.prepared.capstone.preparedjava.models.Recipe;
import com.prepared.capstone.preparedjava.models.Unit;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class AddRecipeForm {

    private MealPlan mealplan;
    private Iterable<Recipe> recipes;

    @NotNull
    private int mealplanId;

    @NotNull
    private int recipeId;



    public AddRecipeForm() {}

    public AddRecipeForm(Iterable<Recipe> recipes, MealPlan mealplan) {
        this.mealplan = mealplan;
        this.recipes = recipes;
    }



    public Iterable<Recipe> getRecipes() { return recipes; }

    public void setRecipes(Iterable<Recipe> recipes) { this.recipes = recipes; }

    public MealPlan getMealplan() { return mealplan; }

    public void setMealplan(MealPlan mealplan) { this.mealplan = mealplan; }

    public int getMealplanId() { return mealplanId; }

    public void setMealplanId(int mealplanId) { this.mealplanId = mealplanId; }

    public int getRecipeId() { return recipeId; }

    public void setRecipeId(int recipeId) { this.recipeId = recipeId; }
}
