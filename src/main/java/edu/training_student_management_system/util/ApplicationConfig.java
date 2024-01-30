package edu.training_student_management_system.util;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import java.util.List;
import java.util.ArrayList;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


// this class is about the application
//about what is gng to do
//swagger- to create app documentation for all api
//add dependencies for swagger  springfox-swagger2 and springfox-swagger-ui
//swagger- this swagger doc is used to explain about api and allows client to see it and use api

@Configuration
@EnableSwagger2
public class ApplicationConfig 
{  @Bean// not calling this method, want spring to call 
	public Docket getDocket()//will take contact info
	{
	Contact contact= new Contact("Kothai", null, "kothai@gmail.com");//(string name,url,email)
	List<VendorExtension> extensions = new ArrayList<>();
	
	ApiInfo apiInfo = new ApiInfo("Student Management System",
			"This is a Student management system Api " 
			  +  "Built using Spring Boot, It is completely restful, "
					+ "built using rest technology.",
					"1.0 first", "", contact,
					"","", extensions);
	//apiinfo -- take input as title,description,version,terms of service url, contact name,license,license url
	//terms of service- eg: free for 6 months like that
	
	return new Docket(DocumentationType.SWAGGER_2).select()
			.apis(RequestHandlerSelectors.basePackage("edu.training_student_management_system"))
			.build().apiInfo(apiInfo).useDefaultResponseMessages(false);
	
	}

}
