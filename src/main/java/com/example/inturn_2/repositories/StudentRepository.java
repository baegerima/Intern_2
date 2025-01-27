package com.example.inturn_2.repositories;

import com.example.inturn_2.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {}