package org.launcode.Code.Food.controller;

import org.launcode.Code.Food.models.Cuisine;
import org.launcode.Code.Food.models.DietaryRestriction;
import org.launcode.Code.Food.models.Recipe;
import org.launcode.Code.Food.models.data.CuisineRepository;
import org.launcode.Code.Food.models.data.DietaryRestrictionRepository;
import org.launcode.Code.Food.models.data.MealTypeRepository;
import org.launcode.Code.Food.models.data.RecipeRepository;
import org.launcode.Code.Food.models.dto.RecipeDietaryRestrictionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;


@Controller
public class HomeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private DietaryRestrictionRepository dietaryRestrictionRepository;

    @Autowired
    private MealTypeRepository mealTypeRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Recipes");
        model.addAttribute("recipes", recipeRepository.findAll());
        return "index";
    }

/*

    @GetMapping("add")
    public String displayAddRecipeForm(Model model) {
        model.addAttribute("title", "Add Recipe");
        model.addAttribute(new Recipe());
        model.addAttribute("cuisines", cuisineRepository.findAll());
        model.addAttribute("dietaryRestrictions", dietaryRestrictionRepository.findAll());
        model.addAttribute("recipeDietaryRestriction", new RecipeDietaryRestrictionDTO());

        return "add";
    }

    @PostMapping("add")
    public String processAddRecipeForm(@ModelAttribute Recipe newRecipe, Errors errors, Model model, @RequestParam int cuisineId, @RequestParam List<Integer> dietaryRestrictions) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            model.addAttribute("cuisines", cuisineRepository.findAll());
            model.addAttribute("dietaryRestrictions", dietaryRestrictionRepository.findAll());
            return "add";
        }

        // verify that cuisineId is in DB. Add to newJob if so, return to Add Job page if not.
        Optional<Cuisine> maybeCuisine = cuisineRepository.findById(cuisineId);
        if (maybeCuisine.isPresent()) {
            Cuisine cuisine = maybeCuisine.get();
            newRecipe.setCuisine(cuisine);
        } else {
            model.addAttribute("title", "Invalid Cuisine ID: " + cuisineId);
            return "add";
        }

        // Loop through all dietaryRestrictionIds in DB. Return to Add Recipe page if any of them are not in DB.
        for (Integer dietaryRestrictionId : dietaryRestrictions) {
            Optional<DietaryRestriction> maybeDietaryRestriction = dietaryRestrictionRepository.findById(dietaryRestrictionId);
            if (maybeDietaryRestriction.isEmpty()) {
                model.addAttribute("title", "Invalid Dietary Restriction ID: " + dietaryRestrictionId);
                return "add";
            }
        }

        List<DietaryRestriction> dietaryRestrictionObjs = (List<DietaryRestriction>) dietaryRestrictionRepository.findAllById(dietaryRestrictions);
        newRecipe.setDietaryRestrictions(dietaryRestrictionObjs);

        newRecipe = recipeRepository.save(newRecipe);

        return "redirect:/view/" + newRecipe.getId();
    }

    @GetMapping("view/{recipeId}")
    public String displayViewRecipe(Model model, @PathVariable int recipeId) {
        Optional<Recipe> result = recipeRepository.findById(recipeId);
        if (result.isPresent()) {
            Recipe recipe = result.get();
            model.addAttribute("title", recipe.getName() + " Details");
            model.addAttribute("recipe", recipe);
            model.addAttribute("cuisine", recipe.getCuisine());
            model.addAttribute("dietaryRestrictions", recipe.getDietaryRestrictions());
        } else {
            model.addAttribute("title", "Invalid Recipe ID: " + recipeId);
        }
        return "view";
    }
*/

}

