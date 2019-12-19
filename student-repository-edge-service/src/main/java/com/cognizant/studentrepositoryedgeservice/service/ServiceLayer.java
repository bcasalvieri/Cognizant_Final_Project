package com.cognizant.studentrepositoryedgeservice.service;

import com.cognizant.studentrepositoryedgeservice.model.Course;
import com.cognizant.studentrepositoryedgeservice.model.Student;
import com.cognizant.studentrepositoryedgeservice.model.StudentViewModel;
import com.cognizant.studentrepositoryedgeservice.util.feign.StudentRepoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceLayer {

    @Autowired
    StudentRepoClient client;

    public StudentViewModel createStudent(StudentViewModel svm) {
        Student student = new Student();
        student.setFirstName(svm.getFirstName());
        student.setLastName(svm.getLastName());
        student = client.createStudent(student);

        svm.setStudentId(student.getStudentId());

        for (Course course : svm.getCourses()) {
            course.setStudent(student);
            client.createCourse(course);
        }

        return svm;
    }

    public StudentViewModel getStudent(Integer id) {
        Student student = client.getStudent(id);
        StudentViewModel svm = new StudentViewModel();
        svm.setStudentId(student.getStudentId());
        svm.setFirstName(student.getFirstName());
        svm.setLastName(student.getLastName());
        svm.setCourses(client.getCoursesByStudentId(student.getStudentId()));
        return svm;
    }
}
