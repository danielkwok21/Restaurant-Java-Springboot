package com.danielkwok.restaurant;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {

    @GetMapping("/")public String getPeople(Model model) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("John", "Doe"));
        people.add(new Person("Jane", "Smith"));
        people.add(new Person("Michael", "Johnson"));

        model.addAttribute("people", people);
        return "people";
    }
}