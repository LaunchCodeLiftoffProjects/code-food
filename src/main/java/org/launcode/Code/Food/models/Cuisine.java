package org.launcode.Code.Food.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cuisine extends AbstractEntity{

    @OneToMany()
    @JoinColumn(name = "cuisine_id")
    private final List<Recipe> recipes = new ArrayList<>();

    public Cuisine() {}

    public List<Recipe> getRecipes() {
        return recipes;
    }

    @Override
    public String toString() {
        return "Cuisine{" +
                "} " + super.toString();
    }
}
