package org.lessons.springlamiapizzeriacrud.controller;

import org.lessons.springlamiapizzeriacrud.model.Ingridient;
import org.lessons.springlamiapizzeriacrud.repository.IngridientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String save() {
        return "redirect:/ingridients";
    }
}
