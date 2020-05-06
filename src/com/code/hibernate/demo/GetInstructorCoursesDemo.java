package com.code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.code.hibernate.demo.entity.Course;
import com.code.hibernate.demo.entity.Instructor;
import com.code.hibernate.demo.entity.InstructorDetail;

public class GetInstructorCoursesDemo 
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
			
			//Start a transaction
			session.beginTransaction();
			
			//Get the instructor from db
			int id = 1;
			
			Instructor tempInstructor = session.get(Instructor.class, id);
			
			//Get all the courses for particular instructor
			System.out.println("Instructor: "+tempInstructor);
			
			System.out.println("Courses: "+tempInstructor.getCourse());
			
			//Commit the Transaction
			session.getTransaction().commit();
			
			
			System.out.println("Done !!!!");
		}
		finally
		{
			session.close();
			
			factory.close();
		}
		
	}

}
