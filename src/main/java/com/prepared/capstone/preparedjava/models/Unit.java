package com.prepared.capstone.preparedjava.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Unit {

    @Id
    @GeneratedValue // Together these mean that id is a unique key id
    private int id;

    @NotNull
    @Size(min=1, max=50, message = "Name must not be empty")
    private String name;

    @Size(max=10)
    private String abbrev;

    @ManyToOne
    private MeasurementType type;

    @OneToMany
    @JoinColumn(name = "unit_id")
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    public Unit() {}

    public Unit(String name, MeasurementType type) {
        this.name = name;
        this.type = type;
    }

    public Unit(String name, String abbrev, MeasurementType type) {
        this(name, type);
        this.abbrev = abbrev;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public MeasurementType getType() { return type; }

    public void setType(MeasurementType type) { this.type = type; }

    public String getAbbrev() { return abbrev; }

    public void setAbbrev(String abbrev) { this.abbrev = abbrev; }

    public List<RecipeIngredient> getRecipeIngredients() { return recipeIngredients; }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) { this.recipeIngredients = recipeIngredients; }
}
