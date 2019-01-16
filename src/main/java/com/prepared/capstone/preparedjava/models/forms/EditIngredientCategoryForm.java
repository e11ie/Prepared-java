package com.prepared.capstone.preparedjava.models.forms;

import com.prepared.capstone.preparedjava.models.IngredientCategory;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EditIngredientCategoryForm {

    private IngredientCategory category;

    @NotNull
    private int categoryId;

    @NotNull
    @Size(min=3, max=60)
    private String name;

    public EditIngredientCategoryForm() {}

    public EditIngredientCategoryForm(IngredientCategory category) {
        this.category = category;
    }

    public IngredientCategory getCategory() { return category; }

    public void setCategory(IngredientCategory category) {
        this.category = category;
    }

    public int getCategoryId() { return categoryId; }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
