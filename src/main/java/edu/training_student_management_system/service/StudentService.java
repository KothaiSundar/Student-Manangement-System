package edu.training_student_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

import edu.training_student_management_system.dao.AdminDao;
import edu.training_student_management_system.dao.StudentDao;
import edu.training_student_management_system.entity.Admin;
import edu.training_student_management_system.entity.Student;
import edu.training_student_management_system.exception.AdminAlreadyExistsException;
import edu.training_student_management_system.exception.AdminNotFoundByIdException;
import edu.training_student_management_system.exception.AlreadyExistsException;
import edu.training_student_management_system.exception.StudentAlreadyExistsException;
import edu.training_student_management_system.exception.StudentNotFoundByIdException;
import edu.training_student_management_system.exception.StudentNotFoundException;
import edu.training_student_management_system.util.ResponseStructure;

@Service
@Validated
public class StudentService 
{
@Autowired
private StudentDao studentDao;

@Autowired
private AdminDao adminDao;

public ResponseEntity<ResponseStructure<Student>> saveStudent(int adminId,Student student)

{   	Admin admin=adminDao.findAdminById(adminId);//finding admin present in DB
if(admin!=null)
{
	
    
			if (studentDao.existsByEmail(student.getStudentEmail())) {
		        throw new StudentAlreadyExistsException("Email already exists");
		    }
			if (adminDao.existsByEmail(student.getStudentEmail())) {
		        throw new AdminAlreadyExistsException("Email already exists");
		    }
			else {
		       List<Student> students=admin.getStudents();//getting preexisting student list
		      students.add(student);//assigning the new student to the exixting student list
		    	admin.setStudents(students);//setting the student list with new student to admin
			  Student student2= studentDao.saveStudent(student);//saving student to the DB
		        adminDao.saveAdmin(admin);//updating the student to admin in database
		  
		
		     
		    	ResponseStructure<Student> responseStructure=new ResponseStructure<>();
		    	responseStructure.setStatusCode(HttpStatus.CREATED.value());
		    	responseStructure.setMessage("Student Saved successfully.");
		    	responseStructure.setData(student2);
		    	return new ResponseEntity<ResponseStructure<Student>>(responseStructure,HttpStatus.CREATED);
		    	}
    
}
	else
		{
			throw new AdminNotFoundByIdException("Failed to save Student");
		}
}


public  ResponseEntity<ResponseStructure<Student>> findStudentById(int studentId)
{
	Student student1= studentDao.findStudentById(studentId);
	if(student1!=null)
	{
		ResponseStructure<Student> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Student Found!");
		responseStructure.setData(student1);
		return new ResponseEntity<ResponseStructure<Student>>(responseStructure,HttpStatus.OK);
	}
	else
		throw new StudentNotFoundByIdException("Failed to find Student");
}


public ResponseEntity<ResponseStructure<Student>> updateStudentById(int studentId,Student student) 
{
	
	
	Student existingStudent=studentDao.findStudentById(studentId);
	
	if(existingStudent!=null) 
	
	{ 
		if (!existingStudent.getStudentEmail().equals(student.getStudentEmail())) 
	
	   {
		 	if (studentDao.existsByEmail(student.getStudentEmail()))
		 	{
		        throw new StudentAlreadyExistsException("Email already exists");
		    }
			if (adminDao.existsByEmail(student.getStudentEmail())) 
			{
		        throw new AdminAlreadyExistsException("Email already exists");
		    }
        }
		
		existingStudent.setStudentName(student.getStudentName());
        
              Student updatedStudentResult = studentDao.updateStudentById( studentId,student);

        if (updatedStudentResult != null) {
            ResponseStructure<Student> responseStructure = new ResponseStructure<>();
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setMessage("Student updated successfully.");
            responseStructure.setData(updatedStudentResult);
            return new ResponseEntity<ResponseStructure<Student>>(responseStructure,HttpStatus.OK);
        }
        
        else {
            throw new StudentNotFoundByIdException("Failed to update Student");
        }
    } 
    
    else {
        throw new StudentNotFoundByIdException("Failed to update Student because student not present");
    }
    
} 



public ResponseEntity<ResponseStructure<Student>> updateStudentByIdUserInterface(int adminId, int studentId, Student student) {
    Admin admin = adminDao.findAdminById(adminId);

    if (admin != null) 
    {
        // Find the existing student by ID
        Student existingStudent = studentDao.findStudentById(studentId);

        if (existingStudent != null)
        {
            // Check if the email is being changed
            if (!existingStudent.getStudentEmail().equals(student.getStudentEmail())) {
                // Check if the new email already exists in the current admin's students
//                if (studentDao.existsByEmailAndAdmin(student.getStudentEmail(), admin)) {
//                    throw new AlreadyExistsException("Email already exists in the current admin's students");
//                }
//
//                // Check if the new email already exists in other admins' students
//                if (studentDao.existsByEmailInOtherAdmins(student.getStudentEmail(), adminId)) {
//                    throw new AlreadyExistsException("Email already exists in other admins' students");
//                }

                // Check if the new email already exists globally
            	if (studentDao.existsByEmail(student.getStudentEmail())) {
    		        throw new StudentAlreadyExistsException("Email already exists");
    		    }
    			if (adminDao.existsByEmail(student.getStudentEmail())) {
    		        throw new AdminAlreadyExistsException("Email already exists");
    		    }
            }
            // Update other attributes
            existingStudent.setStudentName(student.getStudentName());
            // Update other attributes as needed

            // Save the changes
            Student updatedStudentResult = studentDao.updateStudentById( studentId,student);

            if (updatedStudentResult != null) {
                ResponseStructure<Student> responseStructure = new ResponseStructure<>();
                responseStructure.setStatusCode(HttpStatus.OK.value());
                responseStructure.setMessage("Student updated successfully.");
                responseStructure.setData(updatedStudentResult);
                return new ResponseEntity<ResponseStructure<Student>>(responseStructure,HttpStatus.OK);
            }
            else {
                throw new StudentNotFoundByIdException("Failed to update Student");
            }
        } 
        
        else {
            throw new StudentNotFoundByIdException("Failed to update Student because student not present");
        }
        
    } 
    else {
        throw new AdminNotFoundByIdException("Failed to update Student because admin not present");
    }
}
public ResponseEntity<ResponseStructure<Student>> deleteStudent(int studentId)
{// here student is non owning site, so cant delete student directly ..
	//so take adminId who that student belongs to
	//so pass the admin also... both studentid and adminid present in db or not is checked and then stu is deleted
	
	
	Student student= studentDao.findStudentById(studentId);// finding the stu with respective id
	if(student!=null)
	{   
		     
		   
		   
		   Student student5= studentDao.deleteStudent(student);//deleting that student
		 
		    
		   ResponseStructure<Student> responseStructure=new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Student deleted successfully.");
			responseStructure.setData(student5);
			return new ResponseEntity<ResponseStructure<Student>>(responseStructure,HttpStatus.OK);
			
	   
	   
	   
	}
	else
	{
		throw new StudentNotFoundByIdException("Failed to find Student");
	}
}
public ResponseEntity<ResponseStructure<Student>> deleteStudentWithAdmin(int studentId, int adminId)
{// here student is non owning site, so cant delete student directly ..
	//so take adminId who that student belongs to
	//so pass the admin also... both studentid and adminid present in db or not is checked and then stu is deleted
	Student student= studentDao.findStudentById(studentId);// finding the stu with respective id
	if(student!=null)
	{   Admin admin=adminDao.findAdminById(adminId);// finding the adminid of that studentid
	   if(admin!=null)
	       {
		    List<Student> students= admin.getStudents(); //finding list of stu present with that admin
		    //(eg if 5 stu in admin 2 list and storing in students) 
		    
		    students.remove(student);// removing particular stu from admin's student list
		    //(removing 1 student from that 5 students)
		    
		   admin.setStudents(students);//  after removing stu , have to set particular stu list to admin
		   //(now added that 4 students to admin)
		   
		    adminDao.updateAdminById(adminId, admin);// and updating with remaining student list in admin
		    //(here have to update that remaining 4 stu to admindao from admin wch is stored)
		    //(otherwise it will still have 5 students)
		    //sometime update operation happens automatically but update
		    
		   Student student5= studentDao.deleteStudent(student);//deleting that student
		    //(now performing deleting operation.. because the student jus removed have no relation with admin
		    //easy to delete now)
		    
		   ResponseStructure<Student> responseStructure=new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Student deleted successfully.");
			responseStructure.setData(student5);
			return new ResponseEntity<ResponseStructure<Student>>(responseStructure,HttpStatus.OK);
			}
	   
	   
	   else
			{
		   throw new AdminNotFoundByIdException("Failed to find Admin"); // if no admin present with user send
			}
	  
	}
	else
	{
		throw new StudentNotFoundByIdException("Failed to find Student");
	}
}



public ResponseEntity<ResponseStructure<List<Student>>> getStudents(int adminId)
{
Admin admin=adminDao.findAdminById(adminId);

	if(admin!=null)
     {
		
	
	List<Student> students=studentDao.getStudents(adminId);
	
	  if(students!=null)
	  {
		  if(students.isEmpty()) 
		  {
			throw new StudentNotFoundException("no students found for req Id");
          }
		else
		  {
		ResponseStructure<List<Student>> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Students Found!");
		responseStructure.setData(students);
		return new ResponseEntity<ResponseStructure<List<Student>>>(responseStructure,HttpStatus.OK);
	      }
	}
	  else 
	  {
		throw new StudentNotFoundException("Failed to find Students");
      }
}else
{
	throw new AdminNotFoundByIdException("Failed to find students");
}

}

public ResponseEntity<ResponseStructure<Student>> getAllStudentsOfAllAdmin() {
    List<Student> students = studentDao.getAllStudentsOfAllAdmin();

    // Directly perform the necessary transformations without mapToAdminDto
 
    
  
	 
    ResponseStructure<Student> responseStructure = new ResponseStructure<>();
    responseStructure.setStatusCode(HttpStatus.OK.value());
    responseStructure.setMessage("All students displayed!");
    responseStructure.setData(students);
    return new ResponseEntity<ResponseStructure<Student>>(responseStructure,HttpStatus.OK);
}







}