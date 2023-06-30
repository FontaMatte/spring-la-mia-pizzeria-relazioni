package org.lessons.springlamiapizzeriacrud.controller;


import jakarta.validation.Valid;
import org.lessons.springlamiapizzeriacrud.model.Pizza;
import org.lessons.springlamiapizzeriacrud.model.SpecialOffer;
import org.lessons.springlamiapizzeriacrud.repository.PizzaRepository;
import org.lessons.springlamiapizzeriacrud.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequestMapping("/specialOffers")
public class SpecialOfferController {

    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    SpecialOfferRepository specialOfferRepository;

    @GetMapping("/create")
    public String create(@RequestParam("pizzaId") Integer pizzaId, Model model) {
        // Creo nuova offerta
        SpecialOffer specialOffer = new SpecialOffer();
        Optional<Pizza> pizza = pizzaRepository.findById(pizzaId);
        if (pizza.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza con id " + pizzaId + " non trovata");
        specialOffer.setPizza(pizza.get());
        model.addAttribute("specialOffer", specialOffer);
        return "/specialOffers/form";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/specialOffers/form";
        }
        specialOfferRepository.save(formSpecialOffer);
        return "redirect:/pizzas/" + formSpecialOffer.getPizza().getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<SpecialOffer> specialOffer = specialOfferRepository.findById(id);
        if (specialOffer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("specialOffer", specialOffer.get());
        return "/specialOffers/form";
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer, BindingResult bindingResult) {
        Optional<SpecialOffer> specialOfferToEdit = specialOfferRepository.findById(id);
        if (specialOfferToEdit.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        formSpecialOffer.setId(id);
        specialOfferRepository.save(formSpecialOffer);
        return "redirect:/pizzas/" + formSpecialOffer.getPizza().getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<SpecialOffer> specialOfferToDelet = specialOfferRepository.findById(id);
        if (specialOfferToDelet.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        specialOfferRepository.delete(specialOfferToDelet.get());
        return "redirect:/pizzas/" + specialOfferToDelet.get().getPizza().getId();
    }
}
