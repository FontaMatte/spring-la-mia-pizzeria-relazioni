package org.lessons.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.lessons.springlamiapizzeriacrud.model.Ingridient;
import org.lessons.springlamiapizzeriacrud.repository.IngridientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ingridients")
public class IngridientController {
    @Autowired
    private IngridientRepository ingridientRepository;

    @GetMapping
    public String index(Model model, @RequestParam("edit") Optional<Integer> ingridientId) {
        List<Ingridient> ingridientList = ingridientRepository.findAll();
        model.addAttribute("ingridients", ingridientList);
        Ingridient ingridientObj;
        if (ingridientId.isPresent()) {
            Optional<Ingridient> ingridientDb = ingridientRepository.findById(ingridientId.get());
            if (ingridientDb.isPresent()) {
                ingridientObj = ingridientDb.get();
            } else {
                ingridientObj = new Ingridient();
            }
        } else {
            ingridientObj = new Ingridient();
        }
        model.addAttribute("ingridientObj", ingridientObj);
        return "/ingridients/index";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("ingridientObj") Ingridient formIngridient,
                       BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingridients", ingridientRepository.findAll());
            return "/ingridients/index";
        }
        ingridientRepository.save(formIngridient);
        return "redirect:/ingridients";
    }
}
