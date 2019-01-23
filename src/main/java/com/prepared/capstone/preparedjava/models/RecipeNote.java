package com.prepared.capstone.preparedjava.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class RecipeNote {

    @Id
    @GeneratedValue // Together these mean that id is a unique key id
    private int id;

    @NotNull
    @ManyToOne
    private Recipe recipe;

    @NotNull
    @Size(min=3, max=230, message = "Title must be between 3 and 230 characters in length")
    private String title;

    @NotNull
    @Size(min=1, message = "Body must not be empty")
    private String body;



    public RecipeNote() {}

    public RecipeNote(String title, String body) {
        this.title = title;
        this.body = body;
    }



    public int getId() { return id; }

    public Recipe getRecipe() { return recipe; }

    public void setRecipe(Recipe recipe) { this.recipe = recipe; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }
}
