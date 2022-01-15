package org.launcode.Code.Food.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe extends AbstractEntity{

    @ManyToOne
    @NotNull
    private Cuisine cuisine;

    @ManyToMany
    private List<DietaryRestriction> dietaryRestrictions = new ArrayList<>();

    public Recipe(Cuisine cuisine, List<DietaryRestriction> dietaryRestrictions) {
        this.cuisine = cuisine;
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public Recipe() {}

    // Getters and setters.
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

}
