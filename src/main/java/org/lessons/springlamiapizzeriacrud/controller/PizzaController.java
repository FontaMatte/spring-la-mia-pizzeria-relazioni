package org.lessons.springlamiapizzeriacrud.controller;

import org.lessons.springlamiapizzeriacrud.model.Pizza;
import org.lessons.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;

//    @GetMapping
//    public String index(Model model) {
//        List<Pizza> pizzas = pizzaRepository.findAll();
//        model.addAttribute("pizzas", pizzas);
//        return "/pizzas/index";
//    }

    @GetMapping
    public String index(@RequestParam(name = "keyword", required = false) String serachString, Model model) {
        List<Pizza> pizzas;
        if (serachString == null || serachString.isBlank()) {
            pizzas = pizzaRepository.findAll();
        } else {
//            pizzas = pizzaRepository.findByName(serachString);
            pizzas = pizzaRepository.findByNameContainingIgnoreCase(serachString);
        }
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("searchInput", serachString == null ? "" : serachString);
        return "/pizzas/index";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Integer pizzaId, Model model) {
        Optional<Pizza> result = pizzaRepository.findById(pizzaId);
        if (result.isPresent()) {
            model.addAttribute("pizza", result.get());
            return "/pizzas/detail";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Pizza with id " + pizzaId + " not found");
        }
    }
}
