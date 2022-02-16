package org.launcode.Code.Food.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MealType extends AbstractEntity {

    @Size(max = 500, message = "Please enter description of up to 500 characters")
    private String description;


    @ManyToMany(mappedBy = "mealTypes")
    private final List<Recipe> recipes = new ArrayList<>();

    public MealType(String description) {
        this.description = description;
    }

    public MealType() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
    }
}