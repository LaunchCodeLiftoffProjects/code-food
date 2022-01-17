package org.launcode.Code.Food.models;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Recipe extends AbstractEntity{

@NotBlank
public String ingredients;

@NotBlank
public String instructions;

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

    @Override
    public String toString() {
        return "Recipe{" +
                "ingredients='" + ingredients + '\'' +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}
