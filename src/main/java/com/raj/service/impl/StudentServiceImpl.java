package com.raj.service.impl;

import com.raj.dto.CreateStudentRequest;
import com.raj.dto.StudentDto;
import com.raj.dto.UpdateStudentRequest;
import com.raj.entity.Student;
import com.raj.exception.StudentNotFoundException;
import com.raj.mapper.StudentMapper;
import com.raj.repo.StudentRepository;
import com.raj.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found"));
        return studentMapper.toDto(student);
    }

    @Override
    public StudentDto createStudent(CreateStudentRequest request) {
        Student student = Student.builder()
                .name(request.getName())
                .email(request.getEmail())
                .department(request.getDepartment())
                .build();
        return studentMapper.toDto(studentRepository.save(student));
    }

    @Override
    public StudentDto updateStudent(Long id, UpdateStudentRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found"));

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setDepartment(request.getDepartment());

        return studentMapper.toDto(studentRepository.save(student));
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student with ID " + id + " not found");
        }
        studentRepository.deleteById(id);
    }
}
