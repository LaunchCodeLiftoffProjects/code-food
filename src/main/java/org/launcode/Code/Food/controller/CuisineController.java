package org.launcode.Code.Food.controller;

import org.launcode.Code.Food.models.Cuisine;
import org.launcode.Code.Food.models.data.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("cuisine")
public class CuisineController {

    @Autowired
    private CuisineRepository cuisineRepository;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("cuisines",cuisineRepository.findAll());
        return "cuisine/index";
    }

    @GetMapping("add")
    @PreAuthorize("hasAuthority('recipe:write')")
    public String displayAddCuisineForm(Model model) {
        model.addAttribute(new Cuisine());
        return "cuisine/add";
    }

    @PostMapping("add")
    @PreAuthorize("hasAuthority('recipe:write')")
    public String processAddCuisineForm(@ModelAttribute @Valid Cuisine newCuisine,
                                        Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "cuisine/add";
        }
        cuisineRepository.save(newCuisine);
        return "redirect:";
    }

    @GetMapping("view/{cuisineId}")
    public String displayViewCuisine(Model model, @PathVariable int cuisineId) {

        Optional<Cuisine> optCuisine = cuisineRepository.findById(cuisineId);
        if (optCuisine.isPresent()) {
            Cuisine cuisine = (Cuisine) optCuisine.get();
            model.addAttribute("cuisine", cuisine);
            return "cuisine/view";
        } else {
            return "redirect:../";
        }
    }

    @GetMapping("delete")
    @PreAuthorize("hasAuthority('recipe:write')")
    public String displayDeleteCuisineForm(Model model){
        model.addAttribute("title","Delete Cuisine");
        model.addAttribute("cuisines",cuisineRepository.findAll());
        return "cuisine/delete";
    }

    @PostMapping("delete")
    @PreAuthorize("hasAuthority('recipe:write')")
    public String deleteCuisineListings(@RequestParam(required = false) int[] cuisineIds){
        if(cuisineIds!=null) {
            for (int id : cuisineIds) {
                cuisineRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

}

