package com.prepared.capstone.preparedjava.models.forms;

import com.prepared.capstone.preparedjava.models.Ingredient;
import com.prepared.capstone.preparedjava.models.IngredientCategory;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EditIngredientForm {

    private Ingredient ingredient;
    private Iterable<IngredientCategory> categories;

    @NotNull
    @Size(min=3, max=60)
    private String name;

    @NotNull
    private int categoryId;

    @NotNull
    private int ingredientId;

    public EditIngredientForm() {}

    public EditIngredientForm(Iterable<IngredientCategory> categories, Ingredient ingredient) {
        this.categories = categories;
        this.ingredient = ingredient;
    }

    public Iterable<IngredientCategory> getCategories() { return categories; }

    public void setCategories(Iterable<IngredientCategory> categories) { this.categories = categories; }

    public int getIngredientId() { return ingredientId; }

    public void setIngredientId(int ingredientId) { this.ingredientId = ingredientId; }

    public Ingredient getIngredient() { return ingredient; }

    public void setIngredient(Ingredient ingredient) { this.ingredient = ingredient; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getCategoryId() { return categoryId; }

    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
}
