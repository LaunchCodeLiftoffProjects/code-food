package org.launcode.Code.Food.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Cuisine extends AbstractEntity{

    @NotBlank
    @Size(min=3,max=300,message ="location must be between 3 to 300 characters")
    private String description;

    public Cuisine() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Cuisine{" +
                "description='" + description + '\'' +
                "} " + super.toString();
    }
}
