package com.university.system.repository;

import com.university.system.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    Set<Student> findAllByCoursesId(Long courseId);
}
