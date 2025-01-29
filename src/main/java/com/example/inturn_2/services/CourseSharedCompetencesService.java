package com.example.inturn_2.services;

import com.example.inturn_2.entities.CourseSharedCompetences;
import com.example.inturn_2.repositories.CourseSharedCompetencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseSharedCompetencesService {

    private final CourseSharedCompetencesRepository repository;

    @Autowired
    public CourseSharedCompetencesService(CourseSharedCompetencesRepository repository) {
        this.repository = repository;
    }

    // Get all CourseSharedCompetences
    public List<CourseSharedCompetences> getAll() {
        return repository.findAll();
    }

    // Get by ID
    public Optional<CourseSharedCompetences> getById(int id) {
        return repository.findById(id);
    }

    // Create new CourseSharedCompetences
    public CourseSharedCompetences create(CourseSharedCompetences courseSharedCompetences) {
        return repository.save(courseSharedCompetences);
    }

    // Update existing CourseSharedCompetences
    public CourseSharedCompetences update(int id, CourseSharedCompetences newData) {
        return repository.findById(id).map(existing -> {
            existing.setCourse(newData.getCourse());
            existing.setSharedCompetence(newData.getSharedCompetence());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("CourseSharedCompetences not found"));
    }

    // Delete by ID
    public void delete(int id) {
        repository.deleteById(id);
    }
}
