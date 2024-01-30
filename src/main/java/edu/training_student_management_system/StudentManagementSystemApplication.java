package edu.training_student_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}

}
//if application not starts that is url error while running the project
//remove version(8.0.33)  of mysqlconnector j if  version present in  pom.xml and dont update project,just run
//or add version of mysqlconnecotor j if version not present in pom.xml and dont update project,just run
