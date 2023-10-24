package com.university.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    private Long id;

    @NotEmpty
    private String name;

    private String description;

//    private Set<StudentDto> students;
}
