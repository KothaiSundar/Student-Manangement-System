package edu.training_student_management_system.exception;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import edu.training_student_management_system.util.ResponseStructure;
// method to define exception handler is @restcontroller
//when data not found, exception sent with msg to non java dev
@RestControllerAdvice
public class SMSExceptionHandler extends ResponseEntityExceptionHandler
{   //response entity exception handler class- to handle field exception 
	//handlemethodarugumentnotvalid -- inbuilt method of responseentityeexceptionhandler
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request)
       {
		
		List<ObjectError> list = ex.getAllErrors();// getting all errors of this exception, return list of obj error type

         Map<String, String> errors= new HashMap<>();// map to send error msg in  key,value pair(fieldname,msg) are string
    		
        for(ObjectError error: list)//iterating all errors
           {
	                 String message=error.getDefaultMessage();// that is will accept the message written in not empty in admin
                       String fieldName=((FieldError)error).getField();//downcasting error to fielderror,  for wch filed error raised
                       //getfield method -- return fieldname i.e eg:adminemail
                      errors.put(fieldName, message);	
           }
       return new ResponseEntity<> (errors, HttpStatus.BAD_REQUEST);
}
	
	
	/** to print error like
	
	"adminname": "admin name cannot be null"        (filedname,message)
	"adminemail": "admin email cannot be null"
	
	*/
	
	
	
@ExceptionHandler// making this method as exe handler
public ResponseEntity<ResponseStructure<String>> AdminNotFoundById(AdminNotFoundByIdException ex)
{
	//ex is obj of adminnotfoundbyidexception class
ResponseStructure<String> responseStructure = new ResponseStructure<>();
responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
responseStructure.setMessage(ex.getMessage());// message sent in find by id will be sent here (from service)
responseStructure.setData("Admin not found with req id");
return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);

}	
//exception for stu not found with req st id
@ExceptionHandler// making this method as exe handler
public ResponseEntity<ResponseStructure<String>> StudentNotFoundById(StudentNotFoundByIdException ex)
{
	//ex is obj of adminnotfoundbyidexception class
ResponseStructure<String> responseStructure = new ResponseStructure<>();
responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
responseStructure.setMessage(ex.getMessage());// message sent in find by id will be sent here (from service)
responseStructure.setData("Student not found with req id");
return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);

}


@ExceptionHandler
public ResponseEntity<ResponseStructure<String>> StudentNotFound(StudentNotFoundException ex)
{
	//ex is obj of adminnotfoundbyidexception class
ResponseStructure<String> responseStructure = new ResponseStructure<>();
responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
responseStructure.setMessage(ex.getMessage());// message sent in find by id will be sent here (from service)
responseStructure.setData("No Students found with requested Admin id");
return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);

}
@ExceptionHandler
public ResponseEntity<ResponseStructure<String>> StudentAlreadyExists(StudentAlreadyExistsException ex)
{
	//ex is obj of adminnotfoundbyidexception class
ResponseStructure<String> responseStructure = new ResponseStructure<>();
responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
responseStructure.setMessage(ex.getMessage());// message sent in find by id will be sent here (from service)
responseStructure.setData("Already found student by this email id");
return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);

}
@ExceptionHandler
public ResponseEntity<ResponseStructure<String>> AdminAlreadyExists(AdminAlreadyExistsException ex)
{
	//ex is obj of adminnotfoundbyidexception class
ResponseStructure<String> responseStructure = new ResponseStructure<>();
responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
responseStructure.setMessage(ex.getMessage());// message sent in find by id will be sent here (from service)
responseStructure.setData("Already found admin by this email id");
return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);

}

@ExceptionHandler
public ResponseEntity<ResponseStructure<String>> AlreadyExists(AlreadyExistsException ex)
{
	//ex is obj of adminnotfoundbyidexception class
ResponseStructure<String> responseStructure = new ResponseStructure<>();
responseStructure.setStatusCode(HttpStatus.CONFLICT.value());
responseStructure.setMessage(ex.getMessage());// message sent in find by id will be sent here (from service)
responseStructure.setData("Already this email exists");
return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.CONFLICT);

}
}

