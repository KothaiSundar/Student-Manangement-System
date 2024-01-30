package edu.training_student_management_system.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import edu.training_student_management_system.entity.Student;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class AdminUserInterfaceDto {
	private int adminId;
    private String adminName;
    private String adminEmail;
    private String adminPassword; // Include password in the UI DTO
    private List<Student> students;
}
