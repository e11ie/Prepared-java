package com.prepared.capstone.preparedjava.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue // Together these mean that id is a unique key id
    private int id;

    @NotNull
    @Size(min=3, max=230, message = "Title must be between 3 and 230 characters in length")
    private String title;

    @OneToMany
    @JoinColumn(name = "recipe_id")
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "recipe_id")
    private List<RecipeNote> recipeNotes = new ArrayList<>();



    public Recipe() {}

    public Recipe(String title) {
        this.title = title;
    }



    public int getId() { return id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public List<RecipeIngredient> getRecipeIngredients() { return recipeIngredients; }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) { this.recipeIngredients = recipeIngredients; }

    public List<RecipeNote> getRecipeNotes() { return recipeNotes; }

    public void setRecipeNotes(List<RecipeNote> recipeNotes) { this.recipeNotes = recipeNotes; }
}