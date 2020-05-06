package com.code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.code.hibernate.demo.entity.Course;
import com.code.hibernate.demo.entity.Instructor;
import com.code.hibernate.demo.entity.InstructorDetail;
import com.code.hibernate.demo.entity.Review;

public class CreateCourseAndReviewsDemo 
{

	public static void main(String[] args) 
	{
		//Create Session Factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();

		
		
		//Create Session
		Session session = factory.getCurrentSession();
		
		try
		{
			
			//Start a transaction
			session.beginTransaction();
			
			//Create a course
			Course tempCourse = new Course("Pacman");
			
			//Add Some Reviews
			tempCourse.add(new Review("Great Course ..."));
			tempCourse.add(new Review("Best Course ..."));
			tempCourse.add(new Review("Awesome Course ..."));
			//Save The Course and Leverage the Cascade
			
			System.out.println("Saving Course: ");
			System.out.println(tempCourse);
			System.out.println(tempCourse.getReviews());
			session.save(tempCourse);
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
