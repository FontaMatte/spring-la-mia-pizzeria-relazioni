package org.lessons.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.lessons.springlamiapizzeriacrud.model.Ingridient;
import org.lessons.springlamiapizzeriacrud.repository.IngridientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ingridients")
public class IngridientController {
    @Autowired
    private IngridientRepository ingridientRepository;

    @GetMapping
    public String index(Model model) {
        List<Ingridient> ingridientList = ingridientRepository.findAll();
        model.addAttribute("ingridients", ingridientList);
        model.addAttribute("ingridientObj", new Ingridient());
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
