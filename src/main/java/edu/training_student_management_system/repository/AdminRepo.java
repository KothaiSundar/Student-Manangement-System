package edu.training_student_management_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import edu.training_student_management_system.entity.Student;
import edu.training_student_management_system.dto.AdminDto;
import edu.training_student_management_system.entity.Admin;


//creating interface extending jparepository 
//in jparepository defening class where we have to create crud operations
//based on primary key crud operations will work ,so id is int
//so integer is given
//all methods will be created by jparepository 
//if we right click jparepository we can see methods in outline


public interface AdminRepo extends JpaRepository<Admin,Integer> 
{
	
	@Query(value= "select a.students from Admin a where a.adminId=?1")//jpql query
	//1st paramater of admin table
	public Optional <List<Student>> getStudents(int adminId);// returning a list of students
	//if not there return null
	//find admin based on adminid and returning students 
	//that students in query is present in admin class List<Student> students
	 List<Admin> findAllBy();
	 
	 boolean existsByAdminEmail(String email);
	 
	// Admin findAdminByStudentId(int studentId);
	 
	 
}
