package com.cognizant.studentrepositoryservice.dao;

import com.cognizant.studentrepositoryservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
