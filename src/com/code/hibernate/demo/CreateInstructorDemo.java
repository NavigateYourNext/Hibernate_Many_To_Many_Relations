package com.code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.code.hibernate.demo.entity.Course;
import com.code.hibernate.demo.entity.Instructor;
import com.code.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo 
{

	public static void main(String[] args) 
	{
		//Create Session Factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();

		
		
		//Create Session
		Session session = factory.getCurrentSession();
		
		try
		{
			//Create the Objects
			Instructor tempInstructor = new Instructor("Susan","Patel","susan@gmail.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.facebook.com","Love 2 Code");
			
			//Associate Objects Together
			
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//Start a transaction
			session.beginTransaction();
			
			//save the instructor -> this will also save instructionDetails object
			//because of mentioned property CascadeType
			session.save(tempInstructor);
			
			
			//Commit the Transaction
			session.getTransaction().commit();
			
			System.out.println("Instuctor: "+tempInstructor);
			System.out.println("Instuctor Details: "+tempInstructorDetail);
			
			System.out.println("Student Saved in DB");
		}
		finally
		{
			session.close();
			
			factory.close();
		}
		
	}

}
