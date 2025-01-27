package com.example.inturn_2.entities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int grade;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseSharedCompetences> sharedCompetences;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseUniqueCompetences> uniqueCompetences;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<StudentCourse> students;
}
