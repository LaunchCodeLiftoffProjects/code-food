package org.launcode.Code.Food.controller;

import org.launcode.Code.Food.models.DietaryRestriction;
import org.launcode.Code.Food.models.data.DietaryRestrictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("dietaryrestrictions")
public class DietaryRestrictionController {

    @Autowired
    private DietaryRestrictionRepository dietaryRestrictionRepository;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("title","Dietary Restrictions");
        model.addAttribute("dietaryRestrictions", dietaryRestrictionRepository.findAll());
        return "dietaryrestrictions/index";
    }

    @GetMapping("add")
    public String displayAddDietaryRestrictionForm(Model model) {
        model.addAttribute(new DietaryRestriction());
        return "dietaryrestrictions/add";
    }

    @PostMapping("add")
    public String processAddDietaryRestrictionForm(@ModelAttribute @Valid DietaryRestriction newDietaryRestriction, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title","Dietary Restrictions");
            model.addAttribute(newDietaryRestriction);
            return "dietaryrestrictions/add";
        }

        newDietaryRestriction = dietaryRestrictionRepository.save(newDietaryRestriction);
        return "redirect:";
    }

    @GetMapping("view/{dietaryRestrictionId}")
    public String displayViewDietaryRestriction(Model model, @PathVariable int dietaryRestrictionId) {
        Optional optDietaryRestriction = dietaryRestrictionRepository.findById(dietaryRestrictionId);

        if (optDietaryRestriction.isPresent()) {
            DietaryRestriction dietaryRestriction = (DietaryRestriction) optDietaryRestriction.get();
            model.addAttribute("dietaryRestriction", dietaryRestriction);
            return "dietaryrestrictions/view";
        } else {
            return "redirect:../";
        }
    }

    @GetMapping("delete")
    public String displayDeleteDietaryRestrictionForm(Model model){
        model.addAttribute("title","Delete Dietary Restriction");
        model.addAttribute("dietaryRestrictions", dietaryRestrictionRepository.findAll());
        return "dietaryrestrictions/delete";
    }

    @PostMapping("delete")
    public String deleteDietaryRestrictionListings(@RequestParam(required = false) int[] dietaryRestrictionIds){
        if(dietaryRestrictionIds!=null) {
            for (int id : dietaryRestrictionIds) {
                dietaryRestrictionRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

}