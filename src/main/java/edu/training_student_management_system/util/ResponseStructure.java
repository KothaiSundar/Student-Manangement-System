package edu.training_student_management_system.util;
// this class should be of general type that is <T>

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ResponseStructure<T>{
// whenever there is output, sending some message to user
	//more meaningful msg to user if they hit postman
	private int statusCode;
	private String message;
	private Object data;
	
}
