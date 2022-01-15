package org.launcode.Code.Food.models.dto;

import org.launcode.Code.Food.models.DietaryRestriction;
import org.launcode.Code.Food.models.Recipe;

import javax.validation.constraints.NotNull;

public class RecipeDietaryRestrictionDTO {

    @NotNull
    private Recipe recipe;

    @NotNull
    private DietaryRestriction dietaryRestriction;

    public RecipeDietaryRestrictionDTO() {}

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public DietaryRestriction getDietaryRestriction() {
        return dietaryRestriction;
    }

    public void setDietaryRestriction(DietaryRestriction dietaryRestriction) {
        this.dietaryRestriction = dietaryRestriction;
    }
}
