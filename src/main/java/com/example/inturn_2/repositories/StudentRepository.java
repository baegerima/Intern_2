package com.example.inturn_2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.inturn_2.entities.Student;


public interface StudentRepository extends JpaRepository<Student, Integer> {
}
