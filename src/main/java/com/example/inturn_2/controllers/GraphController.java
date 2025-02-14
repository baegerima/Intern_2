package com.example.inturn_2.controllers;

import com.example.inturn_2.entities.*;
import com.example.inturn_2.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class GraphController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/barChart")
    public String barChart(Model model) {
        List<Course> courses = courseService.getAllCourses();
        System.out.println("Fetched courses: " + courses.size());

        Map<String, Double> competencyGrades = new HashMap<>();
        Map<String, Integer> competencyCounts = new HashMap<>();

        for (Course course : courses) {
            int courseGrade = course.getGrade();

            // Process shared competencies
            for (CourseSharedCompetences sharedCompetence : course.getSharedCompetences()) {
                String competenceName = sharedCompetence.getSharedCompetence().getName();
                System.out.println("Processing shared competence: " + competenceName);
                competencyGrades.put(competenceName, competencyGrades.getOrDefault(competenceName, 0.0) + courseGrade);
                competencyCounts.put(competenceName, competencyCounts.getOrDefault(competenceName, 0) + 1);
            }

            // Process unique competencies
            for (CourseUniqueCompetences uniqueCompetence : course.getUniqueCompetences()) {
                String competenceName = uniqueCompetence.getUniqueCompetence().getName();
                System.out.println("Processing unique competence: " + competenceName);
                competencyGrades.put(competenceName, competencyGrades.getOrDefault(competenceName, 0.0) + courseGrade);
                competencyCounts.put(competenceName, competencyCounts.getOrDefault(competenceName, 0) + 1);
            }
        }

        // Calculate average grades
        List<String> competencyNames = new ArrayList<>();
        List<Double> averageGrades = new ArrayList<>();

        for (Map.Entry<String, Double> entry : competencyGrades.entrySet()) {
            String competenceName = entry.getKey();
            double totalGrade = entry.getValue();
            int count = competencyCounts.get(competenceName);
            double averageGrade = count > 0 ? totalGrade / count : 0.0;

            competencyNames.add(competenceName);
            averageGrades.add(averageGrade);
        }

        System.out.println("Competencies: " + competencyNames);
        System.out.println("Average Grades: " + averageGrades);

        model.addAttribute("competencies", competencyNames);
        model.addAttribute("grades", averageGrades);

        return "barChart";
    }
}