package com.prepared.capstone.preparedjava.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue // Together these mean that id is a unique key id
    private int id;

    @NotNull
    @Size(min=1, message = "Name must not be empty")
    private String name;

    @ManyToOne
    private IngredientCategory category;

    public Ingredient() {}

    public Ingredient(String name) {
        this.name = name;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public IngredientCategory getCategory() { return category; }

    public void setCategory(IngredientCategory category) { this.category = category; }
}
