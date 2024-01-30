package edu.training_student_management_system.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.training_student_management_system.entity.Admin;
import edu.training_student_management_system.exception.AdminAlreadyExistsException;
import edu.training_student_management_system.exception.AlreadyExistsException;
import edu.training_student_management_system.repository.AdminRepo;
//@repository--layer interacting with DB 

@Repository
public class AdminDao 

{
	/**spring will create default CRUD Operation methods for
	 * every entity classes that is for saving,deleting ,updating ,getting all and getting single value
	 *  */
//to get obj of adminrepo it is autowired in dao
	
	
	
@Autowired
private AdminRepo adminRepo;
//saveAdmin--wch is accepting admin obj method
		//jus returning to caller method 
//this method is called by AdminController
public Admin saveAdmin(Admin admin)//acecepting admin obj
{
//	 if (existsByEmail(admin.getAdminEmail())) {
//         // Throw an exception or handle the case where the email already exists
//         throw new AlreadyExistsException("Email already exists");
//     }
	return adminRepo.save(admin);// .save  is default inbuilt method by repo
	
}//or Admin admin2=adminRepo.save(admin);
//return admin2;
public Admin findAdminById(int adminId)
{//Optional is a generic class- accept wch type of obj has to accept
	//if any admin not present with req id null will be sent by optional
	// it will not send error, it will send null if no data found
	
	Optional<Admin> optional= adminRepo.findById(adminId);//findbyid is inbuilt method of adminrepo
	//findbyid -- will return optional class having data in it
	
	if(optional.isEmpty())//if optional is empty
	{
		return null;
	}
	else
	{
		Admin admin=optional.get();// if optional not empty- get admin present in optional class and return admin
		return admin;
	}
}
//public Admin findAdminByStudentId(int studentId)
//{
//    return adminRepo.findAdminByStudentId(studentId);
//}
public boolean existsByEmail(String adminEmail)
{
	return adminRepo.existsByAdminEmail(adminEmail);
}

public Admin updateAdminById(int adminId,Admin admin)//  to accept the updated obj from frontend, admin is mentioned
//found id based on admin

{Optional<Admin> optional = adminRepo.findById(adminId);

if(optional.isEmpty())
{
	return null;
}
else
{
	admin.setAdminId(adminId);// setting the adminid passed from front end wch is already exsisting one to admin obj
	return adminRepo.save(admin);// no update method , jus to save data which is updated and to return to adminrepo
}
}




public Admin deleteAdminById(int adminId)
{   Optional<Admin> optional= adminRepo.findById(adminId);
if(optional.isEmpty())
{
	return null;
}
else
{
	adminRepo.deleteById(adminId);
	return optional.get();
}
}


public List<Admin> getAllAdmins() {
    return adminRepo.findAllBy();
}

}

