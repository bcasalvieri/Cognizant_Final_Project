package com.cognizant.studentrepositoryservice.dao;

import com.cognizant.studentrepositoryservice.model.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    Student student;

    @Before
    public void setUp() throws Exception {
        courseRepository.findAll().forEach(course -> courseRepository.deleteById(course.getName()));
        studentRepository.findAll().forEach(student -> studentRepository.deleteById(student.getStudentId()));

        student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student = studentRepository.save(student);
    }

    @Test
    public void createGetGetAllDeleteStudents() {
        assertEquals(student, studentRepository.findById(student.getStudentId()).orElse(null));
        assertEquals(1, studentRepository.findAll().size());

        studentRepository.deleteById(student.getStudentId());
        assertEquals(0, studentRepository.findAll().size());
    }

    @Test
    public void updateStudent() {
        student.setFirstName("Jane");
        student = studentRepository.save(student);

        assertEquals(student, studentRepository.findById(student.getStudentId()).orElse(null));
    }
}