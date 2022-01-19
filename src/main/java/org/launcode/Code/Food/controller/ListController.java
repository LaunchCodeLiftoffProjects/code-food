package org.launcode.Code.Food.controller;


import org.launcode.Code.Food.models.Recipe;
import org.launcode.Code.Food.models.RecipeData;
import org.launcode.Code.Food.models.data.CuisineRepository;
import org.launcode.Code.Food.models.data.DietaryRestrictionRepository;
import org.launcode.Code.Food.models.data.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "list")
public class ListController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private CuisineRepository cuisineRepository;
    
    @Autowired
    private DietaryRestrictionRepository dietaryRestrictionRepository;


    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController () {

        columnChoices.put("all", "All");
        columnChoices.put("cuisine", "Cuisine");
        columnChoices.put("dietaryRestriction", "Dietary Restriction");
        //columnChoices.put("mealType", "Meal Type");
    }

    @RequestMapping("")
    public String list(Model model) {
        model.addAttribute("cuisine", cuisineRepository.findAll());
        model.addAttribute("dietaryRestriction", dietaryRestrictionRepository.findAll());
        //model.addAttribute("mealType", mealRepository.findAll());
        return "list";
    }

    @RequestMapping(value = "recipes")
    public String listRecipesByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
        Iterable<Recipe> recipes;
        if (column.toLowerCase().equals("all")){
            recipes = recipeRepository.findAll();
            model.addAttribute("title", "All Recipes");
        } else {
            recipes = RecipeData.findByColumnAndValue(column, value, recipeRepository.findAll());
            model.addAttribute("title", "Recipes with " + columnChoices.get(column) + ": " + value);
        }

        model.addAttribute("recipes", recipes);

        return "list-recipes";
    }

}
