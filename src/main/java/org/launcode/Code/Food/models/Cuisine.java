package org.launcode.Code.Food.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Cuisine extends AbstractEntity{



    public Cuisine() {
    }



    @Override
    public String toString() {
        return "Cuisine{" +
                "} " + super.toString();
    }
}
