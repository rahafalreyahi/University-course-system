package com.university.system.mapper;

import com.university.system.domain.Student;
import com.university.system.dto.StudentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper extends BaseMapper<StudentDto, Student>{
}
