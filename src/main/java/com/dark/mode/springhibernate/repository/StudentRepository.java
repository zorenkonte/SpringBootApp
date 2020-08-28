package com.dark.mode.springhibernate.repository;

import com.dark.mode.springhibernate.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    @Transactional(readOnly = true)
    Iterable<Student> findByLastName(String lastName);
}
