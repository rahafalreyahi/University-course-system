package com.university.system.mapper;

import com.university.system.domain.Course;
import com.university.system.dto.CourseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper extends BaseMapper<CourseDto, Course>{
}
