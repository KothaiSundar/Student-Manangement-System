package edu.training_student_management_system.entity;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


// obj are directly linked with DB through hibernate
//these obj not used for transferring data

@Entity
@Getter
@Setter
@ToString
public class Admin {
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
private int adminId;
//@NotNull,@NotBlank -- instead of these 2 @notempty is used, combination of both notnull and notblenk

//regex- used to validate string like for email,password---learn in youtube
//@column(unique=true) --learn on youtube for some field validation


@NotEmpty(message="invalid admin name")
private String adminName;



@Email(regexp= "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}")
@NotEmpty(message="invalid adminemail")
//@UniqueEmail
@Column(unique = true)
private String adminEmail;



@Pattern(regexp = "(?=.*[0-9])+(?=.*[a-z])+(?=.*[A-Z])+(?=.*[@#$%^&+=])+(?=\\S+$).{8,}", message = "minimum 8 characters mandatory(1 upperCase,1 lowerCase,1 specialCharacter,1 number)")
@NotEmpty(message="invalid adminpassword")
private String adminPassword;



@OneToMany(cascade=CascadeType.ALL)// to delete student if admin is deleted
@JsonIgnore
private List<Student> students;

//@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//	return "admin email =>" + adminEmail + ":"+ "admin name =>" + adminName;
//
//	}

}
