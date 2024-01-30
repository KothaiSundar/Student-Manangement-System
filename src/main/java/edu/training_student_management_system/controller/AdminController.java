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

import edu.training_student_management_system.dao.AdminDao;
import edu.training_student_management_system.dto.AdminDto;
import edu.training_student_management_system.entity.Admin;
import edu.training_student_management_system.repository.AdminRepo;

import edu.training_student_management_system.service.AdminService;
//@restcontroller--data accepted and transferred in this controller class
//will be in json and xml 
//json--representation of obj
import edu.training_student_management_system.util.ResponseStructure;

//@PostMapping--to save data in DB
//in spring mvc we ll use @modelattribute
//in spring boot we ll use @requestbody

//for all mapping defining url called as Base url
//request mapping is general mapping annotation 
//post,get,put,delete are http specific mapping

//@valid- check for field validation, write whenever @requestbody for obj mentioned
@CrossOrigin("http://localhost:3002")
@RestController
@RequestMapping("/admin")
public class AdminController 
{
	
	@Autowired
	private AdminService adminService;
	
@PostMapping//calling saveAdmin 
public ResponseEntity<ResponseStructure<Admin>> saveAdmin(@Valid @RequestBody Admin admin)//accepting admin obj coming from url /admin
{
	return adminService.saveAdmin(admin);// calling saveAdmin of dao
	
	
}

@GetMapping
public ResponseEntity<ResponseStructure<Admin>> findAdminById(@RequestParam int adminId)
{
	return adminService.findAdminById(adminId);
}


@GetMapping("/adminUser")
public ResponseEntity<ResponseStructure<Admin>> findAdminByIdUserInterface(@RequestParam int adminId)
{
	return adminService.findAdminByIdUserInterface(adminId);
}


@GetMapping("/all")
public ResponseEntity<ResponseStructure<Admin>> getAllAdmins() {
    return adminService.getAllAdmins();
}
//@GetMapping("/student")
//public ResponseEntity<ResponseStructure<Admin>> findAdminByStudentId(@RequestParam int studentId)
//{
//	return adminService.findAdminByStudentId(studentId);
//}


@PutMapping
public ResponseEntity<ResponseStructure<Admin>> updateAdminById(@RequestParam int adminId,@Valid @RequestBody Admin admin)
{
	System.out.println("admin "
			+ " put ");

	return adminService.updateAdminById(adminId,admin);
}
@PutMapping("/adminUser")
public ResponseEntity<ResponseStructure<Admin>> updateAdminByIdUserInterface(@RequestParam int adminId,@Valid @RequestBody Admin admin)
{
	System.out.println("admin "
			+ " put ");

	return adminService.updateAdminByIdUserInterface(adminId,admin);
}
@DeleteMapping
public ResponseEntity<ResponseStructure<Admin>> deleteAdminById(@RequestParam int adminId)
		{
			return adminService.deleteAdminById(adminId);
		}


}

/**instead of entitymanager that and all we are here jus using 3 lines in saveadmin to save
 * from post man if we hit it will come here 
 * and method defined for it will be called--saveAdmin works
 *  here that method retured to admindao 
 *  and in admindao it will be returing to repo to save wch has default method save*/

/** url is carring obj so pass the obj by postman 
 * to pass obj by postman select body--raw--json--
 * write that json form representing obj 
 * then click send and see o/p in postman itself
 * the application should be running in eclipse to pass obj by postman*/

/** whenever the service layer is sending back some data to controller, the controller
 * will be sending back data to client(postman i.e front end), have to send meaningful info
 * therefore response entity is used
 * 
 * 
* ResponseEntity- it is a complete http way of sending the data
* to the client. It is a generic class
* HTTP methods-- POST,GET,PUT,DELETE
* 
* StatusCodes-
* 404(Not found)               - method requested is not found
* 400(Bad Request)             - the method is found, but the requested body or param is not sent
* 200(Ok)                       -  the requested operation is successful// UPDATE AND DELETE
* 302(Found)                    - the data successully  //FIND
* 201(Created)                  - the data is created or Inserted  //INSERT
* 500 (Internal server error)    - the written code in the application will be wrong 
* 
* 
* status code-- easy way to understand in postman instead of reading the msg, if error occurs

*/


/**controller   -will accept req and send res
 * service      - will write logic and everything req for app to perform, it is the main part
 * dao           - will interact will repository
 */

/**  while entering data in postman all fields should be entered
 * that is if for eg admin email is not entered in postman it should not be as null
 * it should send msg that admin email should be entered
 * so field validation should be done
 * for that add dependency  spring boot starter validation in pom.xml
 *and remove version in that so that it gets updated automatically to latest version
 */
