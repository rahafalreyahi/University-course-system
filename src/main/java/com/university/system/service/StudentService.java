package com.university.system.service;

import com.university.system.domain.Student;
import com.university.system.dto.StudentDto;
import com.university.system.mapper.StudentMapper;
import com.university.system.repository.StudentRepo;
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
public class StudentService {

    private final StudentRepo studentRepo;
    private final StudentMapper studentMapper;

    public StudentDto createOrUpdateStudent(StudentDto studentDto){
        log.debug("Request to create or update a student : {}", studentDto);
        Student student = studentRepo.save(studentMapper.toEntity(studentDto));
        return studentMapper.toDto(student);
    }

    public List<StudentDto> getAllStudents(){
        log.debug("Request to get all Students ");
        List<Student> students = studentRepo.findAll();
        return studentMapper.toDto(students);
    }

    public Optional<StudentDto> getStudent(Long id){
        log.debug("Request to get Student by id {} ", id);
        Optional<Student> course = studentRepo.findById(id);
        return course.map(studentMapper::toDto);
    }

    public Set<StudentDto> getAllStudentsByCourseId(Long courseId){
        log.debug("Request to get all Students by course id {} ", courseId);
        Set<Student> students = studentRepo.findAllByCoursesId(courseId);
        return studentMapper.toDto(students);
    }

    public void deleteStudent(Long id){
        log.debug("Request to delete student by id {} ", id);
        studentRepo.deleteById(id);
    }
}
