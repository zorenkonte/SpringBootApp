package com.dark.mode.springhibernate.service;

import com.dark.mode.springhibernate.exception.ResourceNotFoundException;
import com.dark.mode.springhibernate.model.Student;

import java.util.Optional;

public interface StudentService {

    Iterable<Student> all();

    Student save(Student student);

    Student update(Student student) throws ResourceNotFoundException;

    Optional<Student> getStudentById(Integer id) throws ResourceNotFoundException;

    Iterable<Student> findByLastName(String lastName);

    Optional<Student> findTopByEmailLike(String email) throws ResourceNotFoundException;

    void delete(Student student) throws ResourceNotFoundException;

}
