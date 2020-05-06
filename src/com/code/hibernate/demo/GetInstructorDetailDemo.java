package com.code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.code.hibernate.demo.entity.Instructor;
import com.code.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDetailDemo 
{

	public static void main(String[] args) 
	{
		//Create Session Factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();

		
		
		//Create Session
		Session session = factory.getCurrentSession();
		
		try
		{
			
			
			//Start a transaction
			session.beginTransaction();
			
			//Get the instructor Detail Object
			int theId = 200;
			InstructorDetail tempInstructorDetail = 
				session.get(InstructorDetail.class, theId);
			
			//print the instructor detail
			System.out.println("Instructor Detail: "+tempInstructorDetail);
			
			//print the associated instructor
			System.out.println("Associated Instructor: "+tempInstructorDetail.getInstructor());
			
			//Commit the Transaction
			session.getTransaction().commit();
			
			
			System.out.println("Done!!!!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			//handle connection leak issue
			session.close();
			
			factory.close();
		}
		
	}

}
