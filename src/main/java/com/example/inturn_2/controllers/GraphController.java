package com.example.inturn_2.controllers;

import com.example.inturn_2.entities.*;
import com.example.inturn_2.services.CourseService;
import com.example.inturn_2.services.UniqueCompetenceService;
import com.example.inturn_2.services.SharedCompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class GraphController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UniqueCompetenceService uniqueCompetenceService;

    @Autowired
    private SharedCompetenceService sharedCompetenceService;

    @GetMapping("/barChart")
    public String barChart(Model model) {
        // Fetch all unique and shared competencies
        List<UniqueCompetence> uniqueCompetences = uniqueCompetenceService.getAllUniqueCompetences();
        List<SharedCompetence> sharedCompetences = sharedCompetenceService.getAllSharedCompetences();

        // Combine unique and shared competencies into a single list
        List<String> competencies = new ArrayList<>();
        for (UniqueCompetence uc : uniqueCompetences) {
            competencies.add(uc.getName());
        }
        for (SharedCompetence sc : sharedCompetences) {
            competencies.add(sc.getName());
        }

        // Fetch all courses and calculate average grades for competencies
        List<Course> courses = courseService.getAllCourses();
        Map<String, Double> competencyGrades = new HashMap<>();
        Map<String, Integer> competencyCounts = new HashMap<>();

        for (Course course : courses) {
            int courseGrade = course.getGrade();

            // Process unique competencies
            for (CourseUniqueCompetences uniqueCompetence : course.getUniqueCompetences()) {
                String competenceName = uniqueCompetence.getUniqueCompetence().getName();
                competencyGrades.put(competenceName, competencyGrades.getOrDefault(competenceName, 0.0) + courseGrade);
                competencyCounts.put(competenceName, competencyCounts.getOrDefault(competenceName, 0) + 1);
            }

            // Process shared competencies
            for (CourseSharedCompetences sharedCompetence : course.getSharedCompetences()) {
                String competenceName = sharedCompetence.getSharedCompetence().getName();
                competencyGrades.put(competenceName, competencyGrades.getOrDefault(competenceName, 0.0) + courseGrade);
                competencyCounts.put(competenceName, competencyCounts.getOrDefault(competenceName, 0) + 1);
            }
        }

        // Calculate average grades
        List<Double> averageGrades = new ArrayList<>();
        for (String competenceName : competencies) {
            double totalGrade = competencyGrades.getOrDefault(competenceName, 0.0);
            int count = competencyCounts.getOrDefault(competenceName, 0);
            double averageGrade = count > 0 ? totalGrade / count : 0.0;
            averageGrades.add(averageGrade);
        }

        // Add data to the model
        model.addAttribute("competencies", competencies);
        model.addAttribute("grades", averageGrades);

        return "barChart";
    }
}