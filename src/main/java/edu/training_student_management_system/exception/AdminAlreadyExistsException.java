package edu.training_student_management_system.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("serial")// it is not mandatory
@Getter
@AllArgsConstructor
public class AdminAlreadyExistsException extends RuntimeException {
	private String message;
}