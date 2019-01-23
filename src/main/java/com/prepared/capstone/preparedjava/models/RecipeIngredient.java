package com.prepared.capstone.preparedjava.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class RecipeIngredient {


    @Id
    @GeneratedValue // Together these mean that id is a unique key id
    private int id;

    @NotNull
    @ManyToOne
    private Recipe recipe;

    @NotNull
    @ManyToOne
    private Ingredient ingredient;

    @NotNull
    @ManyToOne
    private Unit unit;

    @NotNull
    @Min(0)
    private double amount; // decimal

    private String prep;



    public RecipeIngredient() {}

    public RecipeIngredient(double amount, Unit unit, Ingredient ingredient) {
        this.amount = amount;
        this.unit = unit;
        this.ingredient = ingredient;
    }



    public int getId() { return id; }

    public Recipe getRecipe() { return recipe; }

    public void setRecipe(Recipe recipe) { this.recipe = recipe; }

    public Ingredient getIngredient() { return ingredient; }

    public void setIngredient(Ingredient ingredient) { this.ingredient = ingredient; }

    public Unit getUnit() { return unit; }

    public void setUnit(Unit unit) { this.unit = unit; }

    public double getAmount() { return amount; }

    public void setAmount(double amount) { this.amount = amount; }

    public String getPrep() { return prep; }

    public void setPrep(String prep) { this.prep = prep; }
}
