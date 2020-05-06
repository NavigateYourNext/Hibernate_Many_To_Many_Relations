package com.code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.code.hibernate.demo.entity.Course;
import com.code.hibernate.demo.entity.Instructor;
import com.code.hibernate.demo.entity.InstructorDetail;
import com.code.hibernate.demo.entity.Review;
import com.code.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo 
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
			
			//Create a course
			Course tempCourse = new Course("Pacman");
		
			//Save The Course and Leverage the Cascade
			session.save(tempCourse);
			
			//Create the students
			Student tempStudent1 = new Student("AK","SH","ak@gmail.com");
			Student tempStudent2 = new Student("JJ","SH","ak@gmail.com");
			//Add students to course
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			
			//Save the students
			System.out.println("Saving Students:");
			session.save(tempStudent1);
			session.save(tempStudent2);
			
			System.out.println(tempCourse.getStudents());
			
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
