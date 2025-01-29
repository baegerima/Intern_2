package com.example.inturn_2.services;

import com.example.inturn_2.entities.Course;
import com.example.inturn_2.entities.CourseUniqueCompetences;
import com.example.inturn_2.entities.UniqueCompetence;
import com.example.inturn_2.repositories.CourseUniqueCompetencesRepository;
import com.example.inturn_2.repositories.CourseRepository;
import com.example.inturn_2.repositories.UniqueCompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseUniqueCompetencesService {

    private final CourseUniqueCompetencesRepository repository;
    private final CourseRepository courseRepository;
    private final UniqueCompetenceRepository uniqueCompetenceRepository;

    @Autowired
    public CourseUniqueCompetencesService(CourseUniqueCompetencesRepository repository,
                                          CourseRepository courseRepository,
                                          UniqueCompetenceRepository uniqueCompetenceRepository) {
        this.repository = repository;
        this.courseRepository = courseRepository;
        this.uniqueCompetenceRepository = uniqueCompetenceRepository;
    }

    // Get all CourseUniqueCompetences
    public List<CourseUniqueCompetences> getAll() {
        return repository.findAll();
    }

    // Get by ID
    public Optional<CourseUniqueCompetences> getById(int id) {
        return repository.findById(id);
    }

    // Create new CourseUniqueCompetences
    public CourseUniqueCompetences create(int courseId, int uniqueCompetenceId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        UniqueCompetence uniqueCompetence = uniqueCompetenceRepository.findById(uniqueCompetenceId)
                .orElseThrow(() -> new RuntimeException("Unique Competence not found"));

        CourseUniqueCompetences courseUniqueCompetences = new CourseUniqueCompetences();
        courseUniqueCompetences.setCourse(course);
        courseUniqueCompetences.setUniqueCompetence(uniqueCompetence);

        return repository.save(courseUniqueCompetences);
    }

    // Update existing CourseUniqueCompetences
    public CourseUniqueCompetences update(int id, int courseId, int uniqueCompetenceId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        UniqueCompetence uniqueCompetence = uniqueCompetenceRepository.findById(uniqueCompetenceId)
                .orElseThrow(() -> new RuntimeException("Unique Competence not found"));

        return repository.findById(id).map(existing -> {
            existing.setCourse(course);
            existing.setUniqueCompetence(uniqueCompetence);
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("CourseUniqueCompetences not found"));
    }

    // Delete by ID
    public void delete(int id) {
        repository.deleteById(id);
    }
}
