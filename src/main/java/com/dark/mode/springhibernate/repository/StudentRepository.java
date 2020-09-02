package com.dark.mode.springhibernate.repository;

import com.dark.mode.springhibernate.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {
}
