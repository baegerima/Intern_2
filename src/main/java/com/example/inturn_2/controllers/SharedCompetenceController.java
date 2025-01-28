package com.example.inturn_2.controllers;

import com.example.inturn_2.entities.SharedCompetence;
import com.example.inturn_2.services.SharedCompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shared-competences")
public class SharedCompetenceController {
    @Autowired
    private SharedCompetenceService sharedCompetenceService;

    @GetMapping
    public List<SharedCompetence> getAllSharedCompetences() {
        return sharedCompetenceService.getAllSharedCompetences();
    }

    @GetMapping("/{id}")
    public Optional<SharedCompetence> getSharedCompetenceById(@PathVariable int id) {
        return sharedCompetenceService.getSharedCompetenceById(id);
    }

    @PostMapping("/create")
    public SharedCompetence createSharedCompetence(@RequestBody SharedCompetence sharedCompetence) {
        return sharedCompetenceService.createSharedCompetence(sharedCompetence);
    }

    @PutMapping("/update/{id}")
    public SharedCompetence updateSharedCompetence(@PathVariable int id, @RequestBody SharedCompetence sharedCompetence) {
        return sharedCompetenceService.updateSharedCompetence(id, sharedCompetence);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteSharedCompetence(@PathVariable int id) {
        sharedCompetenceService.deleteSharedCompetence(id);
    }
}