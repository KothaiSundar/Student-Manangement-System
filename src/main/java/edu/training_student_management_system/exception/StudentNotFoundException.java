package edu.training_student_management_system.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("serial")// it is not mandatory..jus to suppress warning
@Getter
@AllArgsConstructor
public class StudentNotFoundException extends RuntimeException {
	private String message;
}
