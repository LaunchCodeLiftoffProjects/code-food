package org.launcode.Code.Food.models;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Recipe extends AbstractEntity{


    @NotBlank
    @Lob // specifies that database should store the property as Large object (long text) instead of varchar
    public String ingredients;

    @NotBlank
    @Lob
    public String instructions;

    @ManyToOne
    private Cuisine cuisine;

    @ManyToMany
    private List<DietaryRestriction> dietaryRestrictions = new ArrayList<>();

    @ManyToMany
    private List<MealType> mealTypes = new ArrayList<>();

    public Recipe() {}

    public Recipe(Cuisine cuisine, List<DietaryRestriction> dietaryRestrictions,
                  List<MealType> mealTypes) {
        this.cuisine = cuisine;
        this.dietaryRestrictions = dietaryRestrictions;
        this.mealTypes = mealTypes;
    }



    @Override
    public String toString() {
        return "Recipe{" +
                "ingredients='" + ingredients + '\'' +
                ", instructions='" + instructions + '\'' +
                '}';
    }

    // Getters and setters.
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public List<DietaryRestriction> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(List<DietaryRestriction> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public void addDietaryRestriction(DietaryRestriction dietaryRestriction) {
        this.dietaryRestrictions.add(dietaryRestriction);
    }

    public List<MealType> getMealTypes() { return mealTypes; }

    public void setMealTypes(List<MealType> mealTypes) {
        this.mealTypes = mealTypes;
    }

    public void addMealType(MealType mealType) {
        this.mealTypes.add(mealType);
    }
}
