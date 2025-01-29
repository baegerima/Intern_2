package com.example.inturn_2.controllers;

import com.example.inturn_2.entities.CourseUniqueCompetences;
import com.example.inturn_2.services.CourseUniqueCompetencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course-unique-competences")
public class CourseUniqueCompetencesController {

    private final CourseUniqueCompetencesService service;

    @Autowired
    public CourseUniqueCompetencesController(CourseUniqueCompetencesService service) {
        this.service = service;
    }

    @GetMapping
    public List<CourseUniqueCompetences> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CourseUniqueCompetences getById(@PathVariable int id) {
        return service.getById(id).orElseThrow(() -> new RuntimeException("CourseUniqueCompetences not found"));
    }

    @PostMapping("/create")
    public CourseUniqueCompetences create(@RequestParam int courseId, @RequestParam int uniqueCompetenceId) {
        return service.create(courseId, uniqueCompetenceId);
    }

    @PutMapping("update/{id}")
    public CourseUniqueCompetences update(@PathVariable int id,
                                          @RequestParam int courseId,
                                          @RequestParam int uniqueCompetenceId) {
        return service.update(id, courseId, uniqueCompetenceId);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
