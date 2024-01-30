package edu.training_student_management_system.repository;



import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.training_student_management_system.entity.Admin;
import edu.training_student_management_system.entity.Student;

public interface StudentRepo extends JpaRepository <Student, Integer>
{
	@Query("SELECT s FROM Student s")
	List<Student> findAllStudents();
	
	 boolean existsByStudentEmail(String email);
//	 boolean existsByEmailAndAdmin(String studentEmail, Admin admin);
//	 boolean existsByEmailInOtherAdmins(String studentEmail, int adminId);
}

