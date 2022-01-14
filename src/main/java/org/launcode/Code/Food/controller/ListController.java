package org.launcode.Code.Food.controller;

import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.JobData;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
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

//    @Autowired
//    private JobRepository jobRepository;
//
//    @Autowired
//    private EmployerRepository employerRepository;
//
//    @Autowired
//    private SkillRepository skillRepository;

    @Autowired
    private DietRepository dietRepository;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private RecipeRepository recipeRepository;


    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController () {

        columnChoices.put("all", "All");
        columnChoices.put("dietaryRestriction", "Dietary Restriction");
        columnChoices.put("mealType", "Meal Type");

    }

    @RequestMapping("")
    public String list(Model model) {
        //left old code so we can see where things connect
        //model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("dietaryRestriction", dietRepository.findAll());
        //model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute("mealType", mealRepository.findAll());
        return "list";
    }

    @RequestMapping(value = "jobs")
    public String listJobsByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
        Iterable<Job> jobs;
        if (column.toLowerCase().equals("all")){
            //jobs = jobRepository.findAll();
            recipes = recipeRepository.findAll();
            model.addAttribute("title", "All Recipes");
        } else {
            //jobs = JobData.findByColumnAndValue(column, value, jobRepository.findAll());
            recipes = RecipeData.findByColumnAndValue(column, value, recipeRepository.findAll());

            model.addAttribute("title", "Recipes with " + columnChoices.get(column) + ": " + value);
        }
        //model.addAttribute("jobs", jobs);
        model.addAttribute("recipes", recipes);


        return "list-jobs";
    }

}
