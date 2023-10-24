package com.university.system.controller;

import com.university.system.dto.CourseDto;
import com.university.system.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("university")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;


    @PostMapping("/courses")
    public ResponseEntity<CourseDto> createCourse(@Valid @RequestBody CourseDto courseDto) throws Exception {
        log.info("REST-Request to create a course : {}", courseDto);
        if (courseDto.getId() != null) {
            throw new Exception("Id value is Not required");
        }
        CourseDto course = courseService.createOrUpdateCourse(courseDto);
        return ResponseEntity.ok().body(course);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable(value = "id") final Long id, @Valid @RequestBody CourseDto courseDto) {
        log.info("REST-Request uto update a course by id : {}, Object : {}", id, courseDto);
        courseDto.setId(id);
        CourseDto course = courseService.createOrUpdateCourse(courseDto);
        return ResponseEntity.ok().body(courseDto);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable Long id) {
        log.info("REST-Request to get course by id : {}", id);
        Optional<CourseDto> courseDto = courseService.getCourse(id);
        return courseDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        log.info("REST-Request to get all courses");
        List<CourseDto> courses = courseService.getAllCourses();
        return ResponseEntity.ok().body(courses);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        log.info("REST-Request to delete course by id : {}", id);
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/{id}/courses")
    public ResponseEntity<Set<CourseDto>> getAllCourses(@PathVariable Long id) {
        log.info("REST-Request to get all courses by student id {} ", id);
        Set<CourseDto> courses = courseService.getAllByStudentId(id);
        return ResponseEntity.ok().body(courses);
    }
}
