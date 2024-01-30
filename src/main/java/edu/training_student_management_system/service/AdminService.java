package edu.training_student_management_system.service;

import java.util.List;

//will write logic in service

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.training_student_management_system.dao.AdminDao;
import edu.training_student_management_system.dao.StudentDao;
import edu.training_student_management_system.dto.AdminDto;
import edu.training_student_management_system.dto.AdminUserInterfaceDto;
import edu.training_student_management_system.entity.Admin;

import edu.training_student_management_system.exception.AdminAlreadyExistsException;
import edu.training_student_management_system.exception.AdminNotFoundByIdException;
import edu.training_student_management_system.exception.AlreadyExistsException;
import edu.training_student_management_system.exception.StudentAlreadyExistsException;
import edu.training_student_management_system.util.ResponseStructure;


@Service
@Validated
public class AdminService
{
@Autowired
private AdminDao adminDao;

@Autowired
private AdminDto adminDto;

@Autowired
private AdminUserInterfaceDto adminUserInterfaceDto;
@Autowired
private StudentDao studentDao;
//@Autowired
//private Student student;

//private static int count = 0;

public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin)
{   // here no if else...whatever we save it should be saved
	
	if(adminDao.existsByEmail(admin.getAdminEmail()))
	{
		throw new AdminAlreadyExistsException("Email already exists");
	}
	if(studentDao.existsByEmail(admin.getAdminEmail()))
	{
		throw new StudentAlreadyExistsException("Email already exists");
	}

	else {
	Admin admin2= adminDao.saveAdmin(admin);
	adminDto.setAdminId(admin2.getAdminId());
	adminDto.setAdminName(admin2.getAdminName());
	adminDto.setAdminEmail(admin2.getAdminEmail());
	adminDto.setStudents(admin2.getStudents());
	
	
	
	ResponseStructure<Admin> responseStructure=new ResponseStructure<>();
	responseStructure.setStatusCode(HttpStatus.CREATED.value());
	responseStructure.setMessage("Admin Saved successfully.");
	responseStructure.setData(adminDto);
	return new ResponseEntity<ResponseStructure<Admin>>(responseStructure,HttpStatus.CREATED);
	
}

}

public ResponseEntity<ResponseStructure<Admin>> findAdminById(int adminId)
{// ResponseEntity is of type ResponseStructure type wch is of type Admin
	Admin admin= adminDao.findAdminById(adminId);
	//count++;
	//AdminDto adminDto = new AdminDto();
	// to check returned admin is null or not
	if(admin!=null )
	{	
		//System.out.println("count: " + count);
		//System.out.println("admin from DB: " + admin.getAdminEmail());

		//if(count < 2) {
		adminDto.setAdminId(admin.getAdminId());
	adminDto.setAdminName(admin.getAdminName());
	adminDto.setAdminEmail(admin.getAdminEmail());
	adminDto.setStudents(admin.getStudents());
		//}
		System.out.println("adminDTo  DB: " + adminDto.getAdminEmail());

		ResponseStructure<Admin> responseStructure=new ResponseStructure<>();//res st type obj Admin
		responseStructure.setStatusCode(HttpStatus.OK.value());// value() method will return integer data of
		//particular http status code ...here status code is for that 302,201,500....
		responseStructure.setMessage("Admin Found!");
		responseStructure.setData(adminDto);// returning dto obj as data
		return new ResponseEntity<ResponseStructure<Admin>>(responseStructure,HttpStatus.OK);
		//here no need to pass value() method because this particular constructor will accept
		//http sts type obj only
	}
	else
	
		throw new AdminNotFoundByIdException("Failed to find Admin");
}

public ResponseEntity<ResponseStructure<Admin>> findAdminByIdUserInterface(int adminId)
{
	Admin admin= adminDao.findAdminById(adminId);

	if(admin!=null )
	{	
		adminDto.setAdminId(admin.getAdminId());
		adminDto.setAdminName(admin.getAdminName());
		adminDto.setAdminEmail(admin.getAdminEmail());
		adminDto.setStudents(admin.getStudents());
        
		ResponseStructure<Admin> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Admin found successfully.");
		
		responseStructure.setData(adminDto);
		return new ResponseEntity<ResponseStructure<Admin>>(responseStructure,HttpStatus.OK);
	}
	else
	
		throw new AdminNotFoundByIdException("Failed to find Admin");
}

//public ResponseEntity<ResponseStructure<Admin>> findAdminByStudentId(int studentId)
//{
//	Admin admin= adminDao.findAdminByStudentId(studentId);
//
//	if(admin!=null )
//	{	
//		adminUserInterfaceDto.setAdminId(admin.getAdminId());
//        adminUserInterfaceDto.setAdminName(admin.getAdminName());
//        adminUserInterfaceDto.setAdminEmail(admin.getAdminEmail());
//        adminUserInterfaceDto.setAdminPassword(admin.getAdminPassword());
//        adminUserInterfaceDto.setStudents(admin.getStudents());
//        
//		ResponseStructure<Admin> responseStructure=new ResponseStructure<>();
//		responseStructure.setStatusCode(HttpStatus.OK.value());
//		responseStructure.setMessage("Admin found successfully.");
//		
//		responseStructure.setData(adminUserInterfaceDto);
//		return new ResponseEntity<ResponseStructure<Admin>>(responseStructure,HttpStatus.OK);
//	}
//	else
//	
//		throw new AdminNotFoundByIdException("Failed to find Admin");
//}


public ResponseEntity<ResponseStructure<Admin>> updateAdminById(int adminId,Admin admin)
{     
	System.out.println("admin ");
	 System.out.println("admin.getAdminEmail(): " + admin.getAdminEmail());

	Admin existingAdmin=adminDao.findAdminById(adminId);
	
	 System.out.println("existingAdmin.getAdminEmail(): " + existingAdmin);
	
	if(existingAdmin!=null)
	{
				if(!existingAdmin.getAdminEmail().equals(admin.getAdminEmail()))
				{
					if(adminDao.existsByEmail(admin.getAdminEmail()))
					{
						throw new AdminAlreadyExistsException("Email already exists");
					}
					if(studentDao.existsByEmail(admin.getAdminEmail()))
					{
						throw new StudentAlreadyExistsException("Email already exists");
					}
					 System.out.println("existingAdmin.getAdminEmail(): " + existingAdmin);
			      }
		
              
  
				
		                existingAdmin.setAdminName(admin.getAdminName());
		               existingAdmin.setAdminEmail(admin.getAdminEmail());
		                 Admin updatedAdmin= adminDao.updateAdminById(adminId, existingAdmin);
					// to check returned admin is null or not
		                 
		                 System.out.println("admin.name: " + updatedAdmin.getAdminName());
		                 
		                 System.out.println("adnin email"+updatedAdmin.getAdminEmail());
		                
		                 
		                 adminDto.setAdminId(updatedAdmin.getAdminId());
							adminDto.setAdminName(updatedAdmin.getAdminName());
							adminDto.setAdminEmail(updatedAdmin.getAdminEmail());
							adminDto.setStudents(updatedAdmin.getStudents());
		                 
						ResponseStructure<Admin> responseStructure=new ResponseStructure<>();
						responseStructure.setStatusCode(HttpStatus.OK.value());
						responseStructure.setMessage("Admin updated successfully.");
						
						responseStructure.setData(adminDto);
						return new ResponseEntity<ResponseStructure<Admin>>(responseStructure,HttpStatus.OK);
				
						
			   
     }
	else
	{
	throw new AdminNotFoundByIdException("Failed to update admin");
	}
}


public ResponseEntity<ResponseStructure<Admin>> updateAdminByIdUserInterface(int adminId,Admin admin)
{     
	System.out.println("admin ");
	 System.out.println("admin.getAdminEmail(): " + admin.getAdminEmail());

	Admin existingAdmin=adminDao.findAdminById(adminId);
	
	 System.out.println("existingAdmin.getAdminEmail(): " + existingAdmin);
	
	if(existingAdmin!=null)
	{
				if(!existingAdmin.getAdminEmail().equals(admin.getAdminEmail()))
				{
					if(adminDao.existsByEmail(admin.getAdminEmail()))
					{
						throw new AdminAlreadyExistsException("Email already exists");
					}
					if(studentDao.existsByEmail(admin.getAdminEmail()))
					{
						throw new StudentAlreadyExistsException("Email already exists");
					}
					 System.out.println("existingAdmin.getAdminEmail(): " + existingAdmin);
			      }
		
              
  
				
		                existingAdmin.setAdminName(admin.getAdminName());
		               existingAdmin.setAdminEmail(admin.getAdminEmail());
		               existingAdmin.setAdminPassword(admin.getAdminPassword());
		                 Admin updatedAdmin= adminDao.updateAdminById(adminId, existingAdmin);
					// to check returned admin is null or not
		                 
		                 System.out.println("admin.name: " + updatedAdmin.getAdminName());
		                 
		                 System.out.println("adnin email"+updatedAdmin.getAdminEmail());
		                
		                 
		                 adminUserInterfaceDto.setAdminId(updatedAdmin.getAdminId());
		                 adminUserInterfaceDto.setAdminName(updatedAdmin.getAdminName());
		                 adminUserInterfaceDto.setAdminEmail(updatedAdmin.getAdminEmail());
		                 adminUserInterfaceDto.setAdminPassword(updatedAdmin.getAdminPassword());
		                 adminUserInterfaceDto.setStudents(updatedAdmin.getStudents());
		                 
						ResponseStructure<Admin> responseStructure=new ResponseStructure<>();
						responseStructure.setStatusCode(HttpStatus.OK.value());
						responseStructure.setMessage("Admin updated successfully.");
						
						responseStructure.setData(adminUserInterfaceDto);
						return new ResponseEntity<ResponseStructure<Admin>>(responseStructure,HttpStatus.OK);
				
						
			   
     }
	else
	{
	throw new AdminNotFoundByIdException("Failed to update admin");
	}
}


public ResponseEntity<ResponseStructure<Admin>> deleteAdminById(int adminId)
{
	Admin admin5= adminDao.deleteAdminById(adminId);
	if(admin5!=null)
	{ adminDto.setAdminId(admin5.getAdminId());
	adminDto.setAdminName(admin5.getAdminName());
	adminDto.setAdminEmail(admin5.getAdminEmail());
	adminDto.setStudents(admin5.getStudents());
		ResponseStructure<Admin> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Admin deleted successfully.");
		responseStructure.setData(adminDto);
		return new ResponseEntity<ResponseStructure<Admin>>(responseStructure,HttpStatus.OK);
	
    }else
    {
    	throw new AdminNotFoundByIdException("Failed to delete admin");
    }
   }



public ResponseEntity<ResponseStructure<Admin>> getAllAdmins() {
    List<Admin> admins = adminDao.getAllAdmins();

    // Directly perform the necessary transformations without mapToAdminDto

  
	 
    ResponseStructure<Admin> responseStructure = new ResponseStructure<>();
    responseStructure.setStatusCode(HttpStatus.OK.value());
    responseStructure.setMessage("All admins displayed!");
    responseStructure.setData(admins);
    return new ResponseEntity<ResponseStructure<Admin>>(responseStructure,HttpStatus.OK);
}


}






