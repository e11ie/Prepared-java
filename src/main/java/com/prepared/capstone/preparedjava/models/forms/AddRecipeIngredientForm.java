package com.prepared.capstone.preparedjava.models.forms;

import com.prepared.capstone.preparedjava.models.Ingredient;
import com.prepared.capstone.preparedjava.models.Recipe;
import com.prepared.capstone.preparedjava.models.Unit;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class AddRecipeIngredientForm {

    private Recipe recipe;
    private Iterable<Unit> units;
    private Iterable<Ingredient> ingredients;

    @NotNull
    @DecimalMin(value="0", inclusive=false)
    private double amount;

    @NotNull
    private int unitId;

    @NotNull
    private int ingredientId;

    @NotNull
    private int recipeId;



    public AddRecipeIngredientForm() {}

    public AddRecipeIngredientForm(Iterable<Unit> units, Iterable<Ingredient> ingredients, Recipe recipe) {
        this.units = units;
        this.ingredients = ingredients;
        this.recipe = recipe;
    }



    public Recipe getRecipe() { return recipe; }

    public void setRecipe(Recipe recipe) { this.recipe = recipe; }

    public Iterable<Unit> getUnits() { return units; }

    public void setUnits(Iterable<Unit> units) { this.units = units; }

    public Iterable<Ingredient> getIngredients() { return ingredients; }

    public void setIngredients(Iterable<Ingredient> ingredients) { this.ingredients = ingredients; }

    public double getAmount() { return amount; }

    public void setAmount(double amount) { this.amount = amount; }

    public int getUnitId() { return unitId; }

    public void setUnitId(int unitId) { this.unitId = unitId; }

    public int getIngredientId() { return ingredientId; }

    public void setIngredientId(int ingredientId) { this.ingredientId = ingredientId; }

    public int getRecipeId() { return recipeId; }

    public void setRecipeId(int recipeId) { this.recipeId = recipeId; }
}
