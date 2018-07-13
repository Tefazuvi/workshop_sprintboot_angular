package com.example.pizzabackend.controller;

import com.example.pizzabackend.domain.Pizza;
import com.example.pizzabackend.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class PizzaController {

    @Autowired
    PizzaService pizzaService;

    @GetMapping(
            value = "/pizza",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPizzas() {
        Optional result = pizzaService.getAllPizzas();
        return result.isPresent() ? new ResponseEntity<>(result.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/pizza",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> insertPizza(@Valid @RequestBody Pizza pizzaDto) {

        pizzaService.insertPizza(pizzaDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/pizza/{pizzaId}")
    public ResponseEntity<?> deletePizza(@PathVariable("pizzaId") String pizzaId) {
        pizzaService.deletePizzaById(pizzaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PatchMapping(value = "/pizza/{pizzaId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePizzaByPrice(@PathVariable("pizzaId") String id,
                                                @RequestBody Map<String, String> price) {
        Optional result = pizzaService.updatePizza(id, Integer.parseInt(price.get("price")));
        return result.isPresent() ? new ResponseEntity<>(result.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
