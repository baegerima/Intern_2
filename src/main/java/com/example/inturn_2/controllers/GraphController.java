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

        Map<String, Double> competencyGrades = new HashMap<>();
        Map<String, Integer> competencyCounts = new HashMap<>();

        for (Course course : courses) {
            int courseGrade = course.getGrade();

            // Обрабатываем общие компетенции
            for (CourseSharedCompetences sharedCompetence : course.getSharedCompetences()) {
                String competenceName = sharedCompetence.getSharedCompetence().getName();
                competencyGrades.put(competenceName, competencyGrades.getOrDefault(competenceName, 0.0) + courseGrade);
                competencyCounts.put(competenceName, competencyCounts.getOrDefault(competenceName, 0) + 1);
            }

            // Обрабатываем уникальные компетенции
            for (CourseUniqueCompetences uniqueCompetence : course.getUniqueCompetences()) {
                String competenceName = uniqueCompetence.getUniqueCompetence().getName();
                competencyGrades.put(competenceName, competencyGrades.getOrDefault(competenceName, 0.0) + courseGrade);
                competencyCounts.put(competenceName, competencyCounts.getOrDefault(competenceName, 0) + 1);
            }
        }

        // Рассчитываем средние оценки
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

        model.addAttribute("competencies", competencyNames);
        model.addAttribute("grades", averageGrades);

        return "barChart";
    }
}