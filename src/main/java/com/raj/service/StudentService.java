package com.raj.service;

import com.raj.dto.CreateStudentRequest;
import com.raj.dto.StudentDto;
import com.raj.dto.UpdateStudentRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    List<StudentDto> getAllStudents();
    StudentDto getStudentById(Long id);
    StudentDto createStudent(CreateStudentRequest request);
    StudentDto updateStudent(Long id, UpdateStudentRequest request);
    void deleteStudent(Long id);
}
