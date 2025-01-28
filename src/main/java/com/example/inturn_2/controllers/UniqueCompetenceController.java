package com.example.inturn_2.controllers;

import com.example.inturn_2.entities.UniqueCompetence;
import com.example.inturn_2.services.UniqueCompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/unique-competences")
public class UniqueCompetenceController {
    @Autowired
    private UniqueCompetenceService uniqueCompetenceService;

    @GetMapping
    public List<UniqueCompetence> getAllUniqueCompetences() {
        return uniqueCompetenceService.getAllUniqueCompetences();
    }

    @GetMapping("/{id}")
    public Optional<UniqueCompetence> getUniqueCompetenceById(@PathVariable int id) {
        return uniqueCompetenceService.getUniqueCompetenceById(id);
    }

    @PostMapping("/create")
    public UniqueCompetence createUniqueCompetence(@RequestBody UniqueCompetence uniqueCompetence) {
        return uniqueCompetenceService.createUniqueCompetence(uniqueCompetence);
    }

    @PutMapping("/update/{id}")
    public UniqueCompetence updateUniqueCompetence(@PathVariable int id, @RequestBody UniqueCompetence uniqueCompetence) {
        return uniqueCompetenceService.updateUniqueCompetence(id, uniqueCompetence);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteUniqueCompetence(@PathVariable int id) {
        uniqueCompetenceService.deleteUniqueCompetence(id);
    }
}