package com.university.system.controller;

import com.university.system.dto.StudentDto;
import com.university.system.service.StudentService;
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
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/students")
    public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto studentDto) throws Exception {
        log.info("REST-Request to create a student : {}", studentDto);
        if (studentDto.getId() != null) {
            throw new Exception("Id value is Not required");
        }
        StudentDto student = studentService.createOrUpdateStudent(studentDto);
        return ResponseEntity.ok().body(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable(value = "id") final Long id, @Valid @RequestBody StudentDto studentDto) {
        log.info("REST-Request uto update a student by id : {}, Object : {}", id, studentDto);
        studentDto.setId(id);
        StudentDto student = studentService.createOrUpdateStudent(studentDto);
        return ResponseEntity.ok().body(student);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long id) {
        log.info("REST-Request to get student by id : {}", id);
        Optional<StudentDto> student = studentService.getStudent(id);
        return student.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/course/{id}/students")
    public ResponseEntity<Set<StudentDto>> getAllStudentsByCourseId(@PathVariable Long id) {
        log.info("REST-Request to get all students by course id {}", id);
        Set<StudentDto> students = studentService.getAllStudentsByCourseId(id);
        return ResponseEntity.ok().body(students);
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        log.info("REST-Request to get all students");
        List<StudentDto> students = studentService.getAllStudents();
        return ResponseEntity.ok().body(students);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        log.info("REST-Request to delete student by id : {}", id);
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
