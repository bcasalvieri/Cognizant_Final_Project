package com.cognizant.studentrepositoryedgeservice.service;

import com.cognizant.studentrepositoryedgeservice.model.Course;
import com.cognizant.studentrepositoryedgeservice.model.Student;
import com.cognizant.studentrepositoryedgeservice.model.StudentViewModel;
import com.cognizant.studentrepositoryedgeservice.util.feign.StudentRepoClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceLayerTest {

    @Mock
    StudentRepoClient client;

    @Autowired
    ServiceLayer service;

    Student student;
    Student outputStudent;
    Course course;
    List<Course> courses;

    @Before
    public void setUp() throws Exception {
        student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");

        outputStudent = new Student();
        outputStudent.setStudentId(1);
        outputStudent.setFirstName("John");
        outputStudent.setLastName("Doe");

        course = new Course();
        course.setName("ENG1001");
        course.setScore(75);
        course.setStudent(outputStudent);

        courses = new ArrayList<>(Collections.singleton(course));
    }

    @Test
    public void createStudent() {
        StudentViewModel svm = new StudentViewModel();
        svm.setFirstName(outputStudent.getFirstName());
        svm.setLastName(outputStudent.getLastName());

    }

    @Test
    public void getStudent() {
    }

    private void setUpMock() {
        when(client.createCourse(course)).thenReturn(course);
        when(client.getCoursesByStudentId(1)).thenReturn(courses);
        when(client.createStudent(student)).thenReturn(outputStudent);
        when(client.getStudent(1)).thenReturn(outputStudent);
    }
}