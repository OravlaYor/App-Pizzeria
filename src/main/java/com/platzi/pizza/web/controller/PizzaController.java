package com.platzi.pizza.web.controller;

import com.platzi.pizza.Persistence.entity.PizzaEntity;
import com.platzi.pizza.Service.PizzaService;
import com.platzi.pizza.Service.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    private  PizzaController (PizzaService pizzaService){
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page
                                                    ,@RequestParam(defaultValue = "8") int elements){
        return ResponseEntity.ok(this.pizzaService.getAll(page, elements));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> get (@PathVariable int idPizza){
        return ResponseEntity.ok(this.pizzaService.getId(idPizza));
    }

    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAvailable(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "8") int elements,
                                                          @RequestParam(defaultValue = "price") String sortBy,
                                                          @RequestParam( defaultValue = "ASC") String sortDirection) {
        return ResponseEntity.ok(this.pizzaService.getAvailable(page, elements, sortBy,sortDirection));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name) {
        return ResponseEntity.ok(this.pizzaService.getByName(name));
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWith(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.getWith(ingredient));
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWithout(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.getWithOut(ingredient));
    }

    @GetMapping("/pricemore/{price}")
    public ResponseEntity<List<PizzaEntity>> getPriceMoreThan(@PathVariable BigDecimal price){
        return ResponseEntity.ok(this.pizzaService.getPriceMoreThan(price));
    }

    @GetMapping("/priceless/{price}")
    public ResponseEntity<List<PizzaEntity>> getPriceLessThan(@PathVariable BigDecimal price){
        return ResponseEntity.ok(this.pizzaService.getPriceLessThan(price));
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getTopLessPrice(@PathVariable BigDecimal price){
        return ResponseEntity.ok(this.pizzaService.getTopLessPrice(price));
    }


    @PostMapping
    public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity pizza) {
        if (pizza.getIdPizza() == null || !this.pizzaService.exists(pizza.getIdPizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update (@RequestBody PizzaEntity pizza){
        if (pizza.getIdPizza() != null && this.pizzaService.exists(pizza.getIdPizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDto dto){
        if (this.pizzaService.exists(dto.getPizzaId())){
             this.pizzaService.updatePrice(dto);
             return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete(@PathVariable int idPizza ){
        if (this.pizzaService.exists(idPizza)){
            this.pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }



}
