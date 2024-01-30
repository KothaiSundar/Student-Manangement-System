package edu.training_student_management_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;




import lombok.Getter;
import lombok.Setter;
//@getter and setter will be used instead of getter and setter
//in some of app it will not work
//so create getters and setters by rightclick itself

//@data--all methods get overridden like tostring,equals
//here not creating dto package rather creating entity packages

@Entity
@Getter
@Setter
public class Student
{   @Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int studentId;

    
    @NotEmpty(message="invalid  student name")
   	private String studentName;
    
   @Email(regexp= "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}")
   @NotEmpty(message="invalid studenEmail")
   @Column(unique = true)
   //@UniqueEmail
	private String studentEmail;
   
//  @ManyToOne
//  //@JoinColumn 
//	//@JsonIgnore//when getting employer, in job --employer not to be printed
//	private Admin admin;


}
