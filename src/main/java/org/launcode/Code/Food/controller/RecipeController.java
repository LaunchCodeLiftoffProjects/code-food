package org.launcode.Code.Food.controller;
import org.launcode.Code.Food.models.Cuisine;
import org.launcode.Code.Food.models.DietaryRestriction;
import org.launcode.Code.Food.models.MealType;
import org.launcode.Code.Food.models.Recipe;
import org.launcode.Code.Food.models.data.CuisineRepository;
import org.launcode.Code.Food.models.data.DietaryRestrictionRepository;
import org.launcode.Code.Food.models.data.MealTypeRepository;
import org.launcode.Code.Food.models.data.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("recipe")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private DietaryRestrictionRepository dietaryRestrictionRepository;

    @Autowired
    private MealTypeRepository mealTypeRepository;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipe/index";
    }

    @GetMapping("add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String displayAddRecipeForm(Model model) {
        model.addAttribute(new Recipe());
        model.addAttribute("cuisines", cuisineRepository.findAll());
        model.addAttribute("dietaryRestrictions", dietaryRestrictionRepository.findAll());
        model.addAttribute("mealTypes", mealTypeRepository.findAll());
        return "recipe/add";
    }

    @PostMapping("add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String processAddRecipeForm(@ModelAttribute @Valid Recipe newRecipe, Errors errors, Model model,
                                       @RequestParam int cuisineId,
                                       @RequestParam List<Integer> dietaryRestrictions,
                                       @RequestParam List<Integer> mealTypes) {

        if (errors.hasErrors()) {
            return "recipe/add";
        }
        Optional<Cuisine> optCuisine = cuisineRepository.findById(cuisineId);
        if(optCuisine.isPresent()) {
            newRecipe.setCuisine(optCuisine.get());
        }

        for (Integer dietaryRestrictionId : dietaryRestrictions) {
            Optional<DietaryRestriction> maybeDietaryRestriction = dietaryRestrictionRepository.findById(dietaryRestrictionId);
            if (maybeDietaryRestriction.isEmpty()) {
                model.addAttribute("title", "Invalid Dietary Restriction ID: " + dietaryRestrictionId);
                return "add";
            }
        }
        List<DietaryRestriction> dietaryRestrictionsObjs = (List<DietaryRestriction>)dietaryRestrictionRepository.findAllById(dietaryRestrictions);
        newRecipe.setDietaryRestrictions(dietaryRestrictionsObjs);

        for (Integer mealTypeId : mealTypes) {
            Optional<MealType> maybeMealType = mealTypeRepository.findById(mealTypeId);
            if (maybeMealType.isEmpty()) {
                model.addAttribute("title", "Invalid Meal Type ID: " + mealTypeId);
                return "add";
            }
        }
        List<MealType> mealTypesObjs = (List<MealType>)mealTypeRepository.findAllById(mealTypes);
        newRecipe.setMealTypes(mealTypesObjs);

        recipeRepository.save(newRecipe);
        return "redirect:/recipe/view/" + newRecipe.getId();
    }

    @GetMapping("view/{recipeId}")
    public String displayViewRecipe(Model model, @PathVariable int recipeId) {

        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        if (optRecipe.isPresent()) {
            Recipe recipe = (Recipe) optRecipe.get();
            model.addAttribute("recipe", recipe);
            model.addAttribute("cuisine", recipe.getCuisine());
            model.addAttribute("dietaryRestrictions", recipe.getDietaryRestrictions());
            model.addAttribute("mealTypes", recipe.getMealTypes());
            return "recipe/view";
        } else {
            return "redirect:../";
        }
    }


    @GetMapping("delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String displayDeleteRecipeForm(Model model){
        model.addAttribute("title","Delete Recipe");
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipe/delete";
    }

    @PostMapping("delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteRecipeListings(@RequestParam(required = false) int[] recipeIds){
        if(recipeIds!=null) {
            for (int id : recipeIds) {
                recipeRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

    @GetMapping("update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String displayUpdateForm(Model model){
        model.addAttribute("title","UpdateRecipes");
        model.addAttribute("recipes",recipeRepository.findAll());
        return"recipe/update";
    }

    @GetMapping("updateDetails")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String displayUpdateDetailsForm(Model model,int recipeId){
        Optional<Recipe> oppRecipeEdit=recipeRepository.findById(recipeId);
        Recipe recipeToEdit=(Recipe) oppRecipeEdit.get();
        model.addAttribute("recipe",recipeToEdit);
        model.addAttribute("cuisines",cuisineRepository.findAll());
        model.addAttribute("dietaryRestrictions",dietaryRestrictionRepository.findAll());
        model.addAttribute("mealTypes",mealTypeRepository.findAll());
        return "recipe/updateDetails";
    }

    @PostMapping("updateDetails")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String processUpdateDetailsForm(@ModelAttribute @Valid Recipe recipeToEdit,Errors errors,Model model,@RequestParam Integer recipeId,@RequestParam(required = false) String name,@RequestParam String ingredients,@RequestParam String instructions,
                                                  @RequestParam Integer cuisine,@RequestParam List<Integer> dietaryRestrictions,@RequestParam List<Integer> mealTypes) {
        if (errors.hasErrors()) {
            model.addAttribute("recipes",recipeRepository.findAll());
            model.addAttribute("cuisines",cuisineRepository.findAll());
            model.addAttribute("dietaryRestrictions",dietaryRestrictionRepository.findAll());
            model.addAttribute("mealTypes",mealTypeRepository.findAll());
            return "recipe/updateDetails";
        }
        Optional<Recipe> oppRecipeEdit = recipeRepository.findById(recipeId);
        recipeToEdit = (Recipe) oppRecipeEdit.get();
        recipeToEdit.setName(name);
        recipeToEdit.setIngredients(ingredients);
        recipeToEdit.setInstructions(instructions);
        Optional<Cuisine> oppCuisine = cuisineRepository.findById(cuisine);
        Cuisine editCuisine = (Cuisine) oppCuisine.get();
        recipeToEdit.setCuisine(editCuisine);
        List<DietaryRestriction> editDietaryRestrictions = (List<DietaryRestriction>) dietaryRestrictionRepository.findAllById(dietaryRestrictions);
        recipeToEdit.setDietaryRestrictions(editDietaryRestrictions);
        List<MealType> editMealTypes = (List<MealType>)mealTypeRepository.findAllById(mealTypes);
        recipeToEdit.setMealTypes(editMealTypes);
        recipeRepository.save(recipeToEdit);
        return "redirect:";
    }

    }


    



