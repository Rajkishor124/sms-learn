package com.raj.dto;

import lombok.Data;

@Data
public class UpdateStudentRequest {
    private String name;
    private String email;
    private String department;
}
