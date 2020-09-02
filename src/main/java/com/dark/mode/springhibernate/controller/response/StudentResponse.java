package com.dark.mode.springhibernate.controller.response;

import com.dark.mode.springhibernate.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentResponse {

    private Iterable<Student> studentList;

    public Iterable<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(Iterable<Student> studentList) {
        this.studentList = studentList;
    }

}
