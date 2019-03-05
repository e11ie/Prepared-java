package com.prepared.capstone.preparedjava.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class MealPlan {

    @Id
    @GeneratedValue // Together these mean that id is a unique key id
    private int id;

    @NotNull
    @Size(min=3, max=230, message = "Name must be between 3 and 230 characters in length")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "mealplan_recipes",
            joinColumns = { @JoinColumn(name = "mealplan_id") },
            inverseJoinColumns = { @JoinColumn(name = "recipe_id") })
    private Set<Recipe> recipes = new HashSet<>();

    public MealPlan () {}

    public MealPlan(String name) {
        this.name = name;
    }


    public void addRecipe(Recipe recipe) { recipes.add(recipe); }
    public void removeRecipe(Recipe recipe) { recipes.remove(recipe); }


    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Set<Recipe> getRecipes() { return recipes; }

    public void setRecipes(Set<Recipe> recipes) { this.recipes = recipes; }
}