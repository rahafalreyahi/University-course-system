package com.university.system.repository;

import com.university.system.domain.Course;
import liquibase.pro.packaged.Q;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {
    @Query("select s.courses from Student s join s.courses where s.id = :studentId")
    Set<Course> findAllByStudentId(Long studentId);
}
