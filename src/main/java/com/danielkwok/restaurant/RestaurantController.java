package com.danielkwok.restaurant;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class RestaurantController {

    List<Food> foods = new ArrayList<>(){{
        add(new Food("Classic Margherita Pizza", 9.99, "hin crust with tomato, mozzarella, and basil.", "/images/pizza.jpg"));
        add(new Food("Juicy Beef Burger", 5.99, "Golden, crispy fries seasoned to perfection.", "/images/burger.jpg"));
        add(new Food("Crispy Golden Fries", 12.99, "Premium beef patty with lettuce, tomato, and cheese.", "/images/fries.jpg"));
    }};

    @GetMapping("/")
    public String getPeople(Model model) {
        model.addAttribute("foods", foods);
        return "index";
    }

    private double totalSum=0;
    Map<String, Food> foodMap = new HashMap<>();
    @GetMapping("/updateSum")
    public String getSum(@RequestParam("id") String id, Model model) {

        Optional<Food> foundFood = foods.stream()
                .filter(food -> food.id.toString().equals(id))
                .findFirst();

        foundFood.ifPresent(food -> {
           foodMap.put(food.id.toString(), food);
        });

        double totalSum = 0.0;
        for (Map.Entry<String, Food> entry : foodMap.entrySet()) {
            Food food= entry.getValue();
            totalSum += food.price;
        }

        model.addAttribute("sum", totalSum);
        return "sum";
    }
}