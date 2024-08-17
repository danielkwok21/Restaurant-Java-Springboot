package com.danielkwok.restaurant;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final Map<HttpSession, List<Food>> foodSelectionBySession ;

    private final Map<UUID, Food> foodByFoodID;

    public RestaurantController() {
        foodByFoodID = new HashMap<>();
        for (Food food:foods){
            foodByFoodID.put(food.id, food);
        }

        foodSelectionBySession = new HashMap<>();
    }

    @GetMapping("/")
    public String getRestaurant(HttpSession session, Model model) {
        // init session
        List<Food> newFoods = new ArrayList<>(foods);
        foodSelectionBySession.put(session, newFoods);

        model.addAttribute("foods", foods);
        return "index";
    }

    @GetMapping("/updateSum")
    public String getSum(@RequestParam("id") String id, HttpSession session, Model model) {
        var foodSelection = foodSelectionBySession.get(session);

        // set user picked this item
        for (Food food : foodSelection) {
            if (food.id.toString().equals(id)) {
                food.isSelected = !food.isSelected;
            }
        }

        double totalSum = 0.0;
        for (Food food : foodSelection) {
            if(food.isSelected) {
                totalSum += food.price;
            }
        }

        model.addAttribute("foods", foodSelection);
        model.addAttribute("sum", totalSum);
        return "sum";
    }
}