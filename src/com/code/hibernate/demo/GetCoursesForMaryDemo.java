package com.code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.code.hibernate.demo.entity.Course;
import com.code.hibernate.demo.entity.Instructor;
import com.code.hibernate.demo.entity.InstructorDetail;
import com.code.hibernate.demo.entity.Review;
import com.code.hibernate.demo.entity.Student;

public class GetCoursesForMaryDemo 
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
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();

		
		
		//Create Session
		Session session = factory.getCurrentSession();
		
		try
		{
			
			//Start a transaction
			session.beginTransaction();
			
			//Get student details from DB
			int studentId = 2;
			Student tempStudent = session.get(Student.class, studentId);
			
			System.out.println("Loaded Student Is: "+tempStudent);
			System.out.println("Assigned Courses: "+tempStudent.getCourses());
			
			
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
