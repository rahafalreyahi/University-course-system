package com.university.system.service;

import com.university.system.domain.Course;
import com.university.system.dto.CourseDto;
import com.university.system.mapper.CourseMapper;
import com.university.system.repository.CourseRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepo courseRepo;
    private final CourseMapper courseMapper;

    public CourseDto createOrUpdateCourse(CourseDto courseDto){
        log.debug("Request to create or update a course : {}", courseDto);
        Course course = courseRepo.save(courseMapper.toEntity(courseDto));
        return courseMapper.toDto(course);
    }

    public List<CourseDto> getAllCourses(){
        log.debug("Request to get all courses ");
        List<Course> courses = courseRepo.findAll();
        return courseMapper.toDto(courses);
    }

    public Optional<CourseDto> getCourse(Long id){
        log.debug("Request to get course by id {} ", id);
        Optional<Course> course = courseRepo.findById(id);

        return course.map(courseMapper::toDto);
    }

    public void deleteCourse(Long id){
        log.debug("Request to delete course by id {} ", id);
        courseRepo.deleteById(id);
    }

    public Set<CourseDto> getAllByStudentId(Long studentId){
        log.debug("Request to get all courses by studentId {} ", studentId);
        Set<Course> courses = courseRepo.findAllByStudentId(studentId);
        return courseMapper.toDto(courses);
    }
}
