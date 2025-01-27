package com.example.inturn_2.entities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "Course_UniqueCompetences")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseUniqueCompetences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "unique_competence_id", nullable = false)
    private UniqueCompetence uniqueCompetence;
}
