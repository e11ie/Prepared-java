package com.prepared.capstone.preparedjava.models.forms;

import com.prepared.capstone.preparedjava.models.IngredientCategory;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddIngredientForm {

    private Iterable<IngredientCategory> categories;

    @NotNull
    @Size(min=3, max=60)
    private String name;

    @NotNull
    private int categoryId;

    public AddIngredientForm() {}

    public AddIngredientForm(Iterable<IngredientCategory> categories) {
        this.categories = categories;
    }

    public Iterable<IngredientCategory> getCategories() { return categories; }

    public void setCategories(Iterable<IngredientCategory> categories) { this.categories = categories; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getCategoryId() { return categoryId; }

    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
}
