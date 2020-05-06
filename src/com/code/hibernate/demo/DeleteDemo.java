package com.code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.code.hibernate.demo.entity.Instructor;
import com.code.hibernate.demo.entity.InstructorDetail;

public class DeleteDemo 
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
			
			//Get instructor by primary key/id
			int theId = 3;
			
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Found Instructor: "+tempInstructor);
			
			
			//Delete the instructor
			if(tempInstructor != null)
			{
				System.out.println("Deleting Instuctor: "+tempInstructor);
				
				//This will also delete instructorDetail because it is cascaded
				session.delete(tempInstructor);
			}
			
			
			//Commit the Transaction
			session.getTransaction().commit();
			
			
			System.out.println("Done!!!!");
		}
		finally
		{
			factory.close();
		}
		
	}

}
