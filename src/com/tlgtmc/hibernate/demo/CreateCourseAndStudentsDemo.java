package com.tlgtmc.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tlgtmc.hibernate.demo.entity.Course;
import com.tlgtmc.hibernate.demo.entity.Instructor;
import com.tlgtmc.hibernate.demo.entity.InstructorDetail;
import com.tlgtmc.hibernate.demo.entity.Review;
import com.tlgtmc.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {

		// Create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();

		// Create session
		Session session = factory.getCurrentSession();

		try {


			session.beginTransaction();
			
			Course tempCourse = new Course("Pacman - How to score one million points?");

			session.save(tempCourse);
			
			Student theStudent = new Student("John","Wick", "jw@gmail.com");
			Student theStudent2 = new Student("Mary","Public", "mp@gmail.com");
			
			tempCourse.addStudent(theStudent);
			tempCourse.addStudent(theStudent2);
			

			session.save(theStudent);
			session.save(theStudent2);
			
			session.getTransaction().commit();
			
			System.out.println("Done!");

		} finally {
			
			session.close();
			factory.close();
		}

	}

}
