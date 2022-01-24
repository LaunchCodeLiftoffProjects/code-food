package org.launcode.Code.Food.models.dto;

import org.launcode.Code.Food.models.MealType;
import org.launcode.Code.Food.models.Recipe;

import javax.validation.constraints.NotNull;

public class RecipeMealTypeDTO {

    @NotNull
    private Recipe recipe;

    @NotNull
    private MealType mealType;

    public RecipeMealTypeDTO() {}

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }
}
