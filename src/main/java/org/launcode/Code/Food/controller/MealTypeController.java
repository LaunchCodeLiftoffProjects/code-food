package org.launcode.Code.Food.controller;

import org.launcode.Code.Food.models.MealType;
import org.launcode.Code.Food.models.data.MealTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("mealtypes")
public class MealTypeController {

    @Autowired
    private MealTypeRepository mealTypeRepository;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("title","Meal Types");
        model.addAttribute("mealTypes", mealTypeRepository.findAll());
        return "mealtypes/index";
    }

    @GetMapping("add")
    public String displayAddMealTypeForm(Model model) {
        model.addAttribute(new MealType());
        return "mealtypes/add";
    }

    @PostMapping("add")
    public String processAddMealTypeForm(@ModelAttribute @Valid MealType newMealType, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title","Meal Types");
            model.addAttribute(newMealType);
            return "mealtypes/add";
        }

        newMealType = mealTypeRepository.save(newMealType);
        return "redirect:";
    }

    @GetMapping("view/{mealTypeId}")
    public String displayViewMealType(Model model, @PathVariable int mealTypeId) {
        Optional optMealType = mealTypeRepository.findById(mealTypeId);

        if (optMealType.isPresent()) {
            MealType mealType = (MealType) optMealType.get();
            model.addAttribute("mealType", mealType);
            return "mealtypes/view";
        } else {
            return "redirect:../";
        }
    }

    @GetMapping("delete")
    public String displayDeleteMealTypeForm(Model model){
        model.addAttribute("title","Delete Meal Type");
        model.addAttribute("mealTypes", mealTypeRepository.findAll());
        return "mealtypes/delete";
    }

    @PostMapping("delete")
    public String deleteMealTypeListings(@RequestParam(required = false) int[] mealTypeIds){
        if(mealTypeIds!=null) {
            for (int id : mealTypeIds) {
                mealTypeRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

}