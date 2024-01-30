package edu.training_student_management_system.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.training_student_management_system.entity.Admin;
import edu.training_student_management_system.entity.Student;
import edu.training_student_management_system.repository.AdminRepo;
import edu.training_student_management_system.repository.StudentRepo;

@Repository
public class StudentDao {

	
	@Autowired
     private StudentRepo studentRepo;
	@Autowired
    private AdminRepo adminRepo;
	
	public Student saveStudent(Student student)
	{
		return studentRepo.save(student);
	}
	public Student deleteStudent(Student student)
	{
		 studentRepo.delete(student);
		 return student;
	}
	
	
	public Student findStudentById(int studentId) {
		Optional<Student> optional= studentRepo.findById(studentId);
		
		if(optional.isEmpty())
		{
			return null;
		}
		else
		{
			Student student=optional.get();
			return student;
		}
	}
	
	
	public Student updateStudentById(int studentId,Student student) {
	
		Optional<Student> optional= studentRepo.findById(studentId);
		
		if(optional.isEmpty())
		{
			return null;
		}
		else
		{
			student.setStudentId(studentId);
			return studentRepo.save(student);
		}
		
	}
	
	
	
	  
	public List<Student> getStudents(int adminId)
	//getting list of students from other entity attribute(admin table-- adminid)
	{// here there will be no inbuilt method in repo because entity is different
		// student and admin both are different entities(tables)
		//getting student list from admin attribute(ID)
		//so write query in admin repo because admin has relationwith stu,not stu has relationship
		
		
		Optional<List<Student>> optional=adminRepo.getStudents(adminId);
	if(optional.isEmpty())
	{
		return null;
	}
	else
	{
		return optional.get();
	}
	
	
	}
	public List<Student> getAllStudentsOfAllAdmin()
		{	
		 return studentRepo.findAllStudents();
		
		}
	
	
	  public boolean existsByEmail(String studentEmail) {
	        return studentRepo.existsByStudentEmail(studentEmail);
	    }
	
	public boolean existsByEmailAndAdmin(String studentEmail, Admin admin) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean existsByEmailInOtherAdmins(String studentEmail, int adminId) {
		// TODO Auto-generated method stub
		return false;
	}
}
