package com.raj.mapper;

import com.raj.dto.StudentDto;
import com.raj.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDto toDto(Student student);

    Student toEntity(StudentDto studentDto);
}
