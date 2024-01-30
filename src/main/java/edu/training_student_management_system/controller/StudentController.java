package edu.training_student_management_system.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.training_student_management_system.entity.Admin;
import edu.training_student_management_system.entity.Student;
import edu.training_student_management_system.service.StudentService;
import edu.training_student_management_system.util.ResponseStructure;

@CrossOrigin("http://localhost:3002")
@RestController
@RequestMapping("/student") 
public class StudentController {

@Autowired
private StudentService studentService;

@PostMapping
public ResponseEntity<ResponseStructure<Student>> saveStudent(@RequestParam int adminId,@Valid @RequestBody Student student)
{System.out.println("student is"+student);
	return studentService.saveStudent(adminId,student);
}


@GetMapping
public ResponseEntity<ResponseStructure<Student>> findStudentById(@RequestParam int studentId)
{
	return studentService.findStudentById(studentId);
}

@GetMapping("/all")
public ResponseEntity<ResponseStructure<Student>> getAllStudentsOfAllAdmin() {
    return studentService.getAllStudentsOfAllAdmin();
}

@PutMapping
public ResponseEntity<ResponseStructure<Student>>  updateStudentById(@Valid @RequestParam int studentId,    @RequestBody Student student)
{
	return studentService.updateStudentById(studentId,student);
}
@PutMapping("/userInterface")
public ResponseEntity<ResponseStructure<Student>>  updateStudentByIdUserInterface(@Valid  @RequestParam int adminId,@RequestParam int studentId,    @RequestBody Student student)
{
	return studentService.updateStudentByIdUserInterface(adminId,studentId,student);
}
@DeleteMapping("/admin")
public ResponseEntity<ResponseStructure<Student>> deleteStudentByWithAdmin(@RequestParam int studentId,@RequestParam int adminId)
{
	return studentService.deleteStudentWithAdmin(studentId,adminId);
}
@DeleteMapping
public ResponseEntity<ResponseStructure<Student>> deleteStudentById(@RequestParam int studentId)
{
	return studentService.deleteStudent(studentId);
}
// if we want to get all the students based on particular adminId

@GetMapping ("/admin")// this url is like from("/student/admin") from student base url to admin mapping
public ResponseEntity<ResponseStructure<List<Student>>> getStudents(@RequestParam int adminId)
{
	return studentService.getStudents(adminId);
}
}
//flow of pgm
//1.controller---2.service---3.dao---4.repo
//then repo return to dao, then dao return to service, then service return to controller

//then at last goes to postman from controller


//request body is to accept obj
//request param is to accept a attribute


