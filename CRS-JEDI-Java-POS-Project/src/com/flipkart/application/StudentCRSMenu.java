package com.flipkart.application;

import java.util.*;


import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.business.NotificationInterface;
import com.flipkart.business.NotificationOperation;
import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.business.RegistrationInterface;
import com.flipkart.business.RegistrationOperation;
import com.flipkart.constant.Grade;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;


public class StudentCRSMenu {
	Scanner sc = new Scanner(System.in);
	RegistrationInterface registrationInterface = RegistrationOperation.getInstance();
	ProfessorInterface professorInterface = ProfessorOperation.getInstance();
	NotificationInterface notificationInterface=NotificationOperation.getInstance();
	private boolean is_registered;

	public void create_menu(int studentId)
	{
		is_registered = getRegistrationStatus(studentId);
		while (CRSApplication.loggedin)
		{
			System.out.println("**************************************");
			System.out.println("*********    Student Menu    *********");
			System.out.println("**************************************");
			System.out.println("      1. Course Registration");
			System.out.println("      2. Add Course");
			System.out.println("      3. Drop Course");
			System.out.println("      4. View Course");
			System.out.println("      5. View Registered Courses");
			System.out.println("      6. View grade card");
			System.out.println("      7. Make Payment");
			System.out.println("      8. Logout");
			System.out.println("**************************************");

			int choice = sc.nextInt();

			switch (choice) {
				case 1:
					registerCourses(studentId);
					break;

				case 2:
					addCourse(studentId);
					break;

				case 3:

					dropCourse(studentId);
					break;

				case 4:
					viewCourse(studentId);
					break;

				case 5:
					viewRegisteredCourse(studentId);
					break;

				case 6:
					viewGradeCard(studentId);
					break;

				case 7:
					make_payment(studentId);
					break;

				case 8:
					CRSApplication.loggedin = false;
					return;

				default:
					System.err.println("***** Wrong Choice *****");
			}
		}
	}


	private void registerCourses(int studentId)
	{

		if(is_registered)
		{
			System.out.println(" Registration is already completed");
			return;
		}

		int count = 0;
		while(count < 6)
		{
			try
			{
				List<Course> courseList=viewCourse(studentId);

				if(courseList==null)
					return;

				System.out.println("Enter Course Code : " + (count+1));
				String courseCode = sc.next();

				if(registrationInterface.addCourse(courseCode,studentId,courseList))
				{
					System.out.println("Course " + courseCode + " registered sucessfully.");
					count++;
				}
				else
				{
					System.out.println(" You have already registered for Course : " + courseCode);
				}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}

		System.out.println("Registration Successful");
		is_registered = true;

		try
		{
			registrationInterface.setRegistrationStatus(studentId);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

	}

	private void addCourse(int studentId)
	{
		if(is_registered)
		{
			List<Course> availableCourseList=viewCourse(studentId);

			if(availableCourseList==null)
				return;

			try
			{
				System.out.println("Enter Course Code : " );
				String courseCode = sc.next();
				if(registrationInterface.addCourse(courseCode, studentId,availableCourseList))
				{
					System.out.println(" You have successfully registered for Course : " + courseCode);
				}
				else
				{
					System.out.println(" You have already registered for Course : " + courseCode);
				}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		else
		{
			System.out.println("Please complete registration");
		}

	}

	private boolean getRegistrationStatus(int studentId)
	{
		try
		{
			return registrationInterface.getRegistrationStatus(studentId);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return false;
	}

	private void dropCourse(int studentId)
	{
		if(is_registered)
		{
			List<Course> registeredCourseList=viewRegisteredCourse(studentId);

			if(registeredCourseList==null)
				return;

			System.out.println("Enter the Course Code : ");
			String courseCode = sc.next();

			try
			{
				registrationInterface.dropCourse(courseCode, studentId,registeredCourseList);
				System.out.println("You have successfully dropped Course : " + courseCode);

			}
			catch (Exception e)
			{

				System.out.println(e.getMessage());
			}
		}
		else
		{
			System.out.println("Please complete registration");
		}

	}

	private List<Course> viewCourse(int studentId)
	{
		List<Course> course_available=null;
		try
		{
			course_available = registrationInterface.viewCourses(studentId);
		}
		catch (Exception e)
		{

			System.out.println(e.getMessage());
		}


		if(course_available.isEmpty())
		{
			System.out.println("NO COURSE AVAILABLE");
			return null;
		}


		System.out.println(String.format("%-20s %-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR", "SEATS"));
		for(Course obj : course_available)
		{
			System.out.println(String.format("%-20s %-20s %-20s %-20s",obj.getCourseCode(), obj.getCourseName(),obj.getProfessorId(), obj.getSeats()));
		}

		return course_available;

	}


	private List<Course> viewRegisteredCourse(int studentId)
	{
		List<Course> course_registered=null;
		try
		{
			course_registered = registrationInterface.viewRegisteredCourses(studentId);
		}
		catch (Exception e)
		{

			System.out.println(e.getMessage());
		}

		if(course_registered.isEmpty())
		{
			System.out.println("You haven't registered for any course");
			return null;
		}

		System.out.println(String.format("%-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR"));

		for(Course obj : course_registered)
		{


			System.out.println(String.format("%-20s %-20s %-20s ",obj.getCourseCode(), obj.getCourseName(),professorInterface.getProfessorById(obj.getProfessorId())));
		}

		return course_registered;
	}


	private void viewGradeCard(int studentId)
	{
		List<Grade> grade_card=null;
		try
		{
			grade_card = registrationInterface.viewGradeCard(studentId);
		}
		catch (Exception e)
		{

			System.out.println(e.getMessage());
		}

		System.out.println(String.format("%-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "GRADE"));

		if(grade_card.isEmpty())
		{
			System.out.println("You haven't registered for any course");
			return;
		}

		for(GradeCard obj : grade_card)
		{
			System.out.println(String.format("%-20s %-20s %-20s",obj.getCourseCode(), obj.getCourseName(),obj.getGrade()));
		}
	}

	private void make_payment(int studentId)
	{

		double fee =0.0;
		try
		{
			fee=registrationInterface.calculateFee(studentId);
		}
		catch (Exception e)
		{

			System.out.println(e.getMessage());
		}

		if(fee == 0.0)
		{
			System.out.println("You have not  registered for any courses yet");
		}
		else
		{

			System.out.println("Your total fee  = " + fee);
			System.out.println("Want to continue Fee Payment(y/n)");
			String ch = sc.next();
			if(ch.equals("y"))
			{
				System.out.println("Select Mode of Payment:");

				int index = 1;
				for(ModeOfPayment mode : ModeOfPayment.values())
				{
					System.out.println(index + " " + mode);
					index = index + 1;
				}

				ModeOfPayment mode = ModeOfPayment.getModeofPayment(sc.nextInt());

				if(mode == null)
					System.out.println("Invalid Input");
				else
				{
					try
					{
						notificationInterface.sendNotification(NotificationType.PAYMENT, studentId, mode, fee);
					}
					catch (Exception e)
					{

						System.out.println(e.getMessage());
					}
				}

			}

		}

	}


}
