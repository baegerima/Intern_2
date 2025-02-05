package com.example.inturn_2.services;

import com.example.inturn_2.entities.Course;
import com.example.inturn_2.entities.CourseSharedCompetences;
import com.example.inturn_2.entities.SharedCompetence;
import com.example.inturn_2.repositories.CourseSharedCompetencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.inturn_2.repositories.CourseRepository;
import com.example.inturn_2.repositories.SharedCompetenceRepository;

import java.util.List;
import java.util.Optional;


@Service
public class CourseSharedCompetencesService {

    private final CourseSharedCompetencesRepository repository;
    private final CourseRepository courseRepository;
    private final SharedCompetenceRepository sharedCompetenceRepository;

    @Autowired
    public CourseSharedCompetencesService(CourseSharedCompetencesRepository repository, CourseRepository courseRepository, SharedCompetenceRepository sharedCompetenceRepository) {
        this.repository = repository;
        this.courseRepository = courseRepository;
        this.sharedCompetenceRepository = sharedCompetenceRepository;
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
    public CourseSharedCompetences create(int courseId, int sharedCompetenceId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Курс не найден"));

        SharedCompetence sharedCompetence = sharedCompetenceRepository.findById(sharedCompetenceId)
                .orElseThrow(() -> new RuntimeException("Компетенция не найдена"));

        CourseSharedCompetences newEntry = new CourseSharedCompetences();
        newEntry.setCourse(course);
        newEntry.setSharedCompetence(sharedCompetence);

        return repository.save(newEntry);
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
