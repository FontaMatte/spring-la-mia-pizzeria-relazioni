package org.lessons.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.lessons.springlamiapizzeriacrud.messages.AlertMessage;
import org.lessons.springlamiapizzeriacrud.messages.AlertMessageType;
import org.lessons.springlamiapizzeriacrud.model.Pizza;
import org.lessons.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
//        Optional<Pizza> result = pizzaRepository.findById(pizzaId);
//        if (result.isPresent()) {
//            model.addAttribute("pizza", result.get());
//            return "/pizzas/detail";
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                    "Pizza with id " + pizzaId + " not found");
//        }
        Pizza pizza = getPizzaById(pizzaId);
        model.addAttribute("pizza", pizza);
        return "/pizzas/detail";
    }

    // CREATE METHODS
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "/pizzas/edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/pizzas/edit";
        }
        pizzaRepository.save(formPizza);
        return "redirect:/pizzas";
    }

    // UPDATE METHODS
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Integer id, Model model) {
        // controllo se la pizza con quell' id esiste
        Pizza pizza = getPizzaById(id);
        model.addAttribute("pizza", pizza);
        return "/pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable Integer id,
            @Valid @ModelAttribute("pizza") Pizza formPizza,
            BindingResult bindingResult, Model model) {
        Pizza pizzaToEdit = getPizzaById(id); // vecchia versione della pizza
        // controllo se si sono verificati errori nella compilazione del form
        if (bindingResult.hasErrors()) {
            return "/pizzas/edit";
        }
        // trasferisco su formPizza tutti i valori dei campi che non sono  presenti nel form
        formPizza.setId(pizzaToEdit.getId()); // nuova versione della Pizza
        // salvo i dati
        pizzaRepository.save(formPizza);
        return "redirect:/pizzas";
    }

    // DELETE METHOD
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        Pizza pizzaToDelete = getPizzaById(id);
        pizzaRepository.delete(pizzaToDelete);
        // aggiungo messaggio di successo
        redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessageType.SUCCESS,
                "pizza " + pizzaToDelete.getName() + " deleted!"));
        return "redirect:/pizzas";
    }

    // PRIVATE METHODS
    // metodo che verifica se la pizza con quell' id esiste
    private Pizza getPizzaById(Integer id) {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id" + id + "not found");
        }
        return result.get();
    }

}
