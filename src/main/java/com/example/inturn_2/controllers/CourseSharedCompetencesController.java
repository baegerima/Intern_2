package com.example.inturn_2.controllers;

import com.example.inturn_2.entities.CourseSharedCompetences;
import com.example.inturn_2.services.CourseSharedCompetencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course-shared-competences")
public class CourseSharedCompetencesController {

    private final CourseSharedCompetencesService service;

    @Autowired
    public CourseSharedCompetencesController(CourseSharedCompetencesService service) {
        this.service = service;
    }

    // Get all CourseSharedCompetences
    @GetMapping
    public List<CourseSharedCompetences> getAll() {
        return service.getAll();
    }

    // Get CourseSharedCompetences by ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseSharedCompetences> getById(@PathVariable int id) {
        Optional<CourseSharedCompetences> courseSharedCompetences = service.getById(id);
        return courseSharedCompetences.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create new CourseSharedCompetences
    @PostMapping("/create")
    public ResponseEntity<CourseSharedCompetences> create(@RequestBody CourseSharedCompetences courseSharedCompetences) {
        CourseSharedCompetences created = service.create(courseSharedCompetences);
        return ResponseEntity.ok(created);
    }

    // Update existing CourseSharedCompetences
    @PutMapping("update/{id}")
    public ResponseEntity<CourseSharedCompetences> update(@PathVariable int id, @RequestBody CourseSharedCompetences courseSharedCompetences) {
        CourseSharedCompetences updated = service.update(id, courseSharedCompetences);
        return ResponseEntity.ok(updated);
    }

    // Delete CourseSharedCompetences by ID
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
