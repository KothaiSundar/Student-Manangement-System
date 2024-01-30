package edu.training_student_management_system.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
// this class is if the admin is not present it should send meaningful message
// super class should be runtimeexception

@SuppressWarnings("serial")// it is not mandatory
@Getter
@AllArgsConstructor
public class AdminNotFoundByIdException extends RuntimeException {
private String message;





	

}
