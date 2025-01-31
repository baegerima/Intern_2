package com.example.inturn_2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class GraphController {

    @GetMapping("/barChart")
    public String barChart(Model model) {
        Map<String, Integer> data = new LinkedHashMap<>();
        data.put("Ashish", 30);
        data.put("Ankit", 50);
        data.put("Gurpreet", 70);
        data.put("Mohit", 90);
        data.put("Manish", 25);

        model.addAttribute("keySet", data.keySet().toArray(new String[0])); // Массив строк
        model.addAttribute("values", data.values().toArray(new Integer[0])); // Массив чисел
        return "barChart";
    }
}


