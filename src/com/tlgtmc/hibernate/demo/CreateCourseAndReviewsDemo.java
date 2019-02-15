package com.tlgtmc.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tlgtmc.hibernate.demo.entity.Course;
import com.tlgtmc.hibernate.demo.entity.Instructor;
import com.tlgtmc.hibernate.demo.entity.InstructorDetail;
import com.tlgtmc.hibernate.demo.entity.Review;

public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {

		// Create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();

		// Create session
		Session session = factory.getCurrentSession();

		try {


			session.beginTransaction();
			
			Course tempCourse = new Course("Pacman - How to score one million points?");
			
			tempCourse.addReview(new Review("Great course!"));
			tempCourse.addReview(new Review("Loving this course!"));
			tempCourse.addReview(new Review("What a lovely course"));

			session.save(tempCourse);
			
			session.getTransaction().commit();
			
			System.out.println("Done!");

		} finally {
			
			session.close();
			factory.close();
		}

	}

}
