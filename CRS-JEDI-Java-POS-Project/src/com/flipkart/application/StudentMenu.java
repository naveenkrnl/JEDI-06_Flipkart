package com.flipkart.client;

import java.util.*;
import java.util.Scanner;


import com.flipkart.application.MainApplication;
import com.flipkart.bean.Course;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;
import com.flipkart.business.NotificationInterface;
import com.flipkart.business.NotificationOperation;
import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.business.RegistrationInterface;
import com.flipkart.business.RegistrationOperation;


public class StudentMenu {
	Scanner sc = new Scanner(System.in);
	RegistrationInterface registrationInterface = RegistrationOperation.getInstance();
	ProfessorInterface professorInterface = ProfessorOperation.getInstance();
	NotificationInterface notificationInterface=NotificationOperation.getInstance();
	private boolean is_registered;
	
	public void create_menu(int studentId)
	{

		is_registered = getRegistrationStatus(studentId);
		while (MainApplication.loggedin)
		{
			System.out.println("*****************************");
			System.out.println("**********Student Menu*********");
			System.out.println("*****************************");
			System.out.println("1. Course Registration");
			System.out.println("2. Add Course");
			System.out.println("3. Drop Course");
			System.out.println("4. View Course");
			System.out.println("5. View Registered Courses");
			System.out.println("6. View grade card");
			System.out.println("7. Make Payment");
			System.out.println("8. Logout");
			System.out.println("*****************************");

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
        
        System.out.println("Enter Course Code : ");
        String courseCode = sc.next();

        registrationInterface.addCourse(courseCode,studentId,courseList)
        
        System.out.println("Course " + courseCode + " registered sucessfully.");            
    
	}
	
	private void addCourse(int studentId)	
	{
		
        System.out.println("Enter Course Code : " );
        String courseCode = sc.next();
        
        System.out.println(" You have successfully registered for Course : " + courseCode);
		
	}
	
	private boolean getRegistrationStatus(int studentId)
	{
		return registrationInterface.getRegistrationStatus(studentId);
	}
	
	private void dropCourse(int studentId)
	{
		
			System.out.println("Enter the Course Code : ");
			String courseCode = sc.next();
			
			System.out.println("You have successfully dropped Course : " + courseCode);

	}
	
	private List<Course> viewCourse(int studentId)
	{
		List<Course> course_available = registrationInterface.viewCourses(studentId);
	
        System.out.println("The following courses are available:");
        return null;
		
		return course_available;

	}
	
	private List<Course> viewRegisteredCourse(int studentId)
	{
		
		List<Course> course_registered = registrationInterface.viewRegisteredCourses(studentId);

		System.out.println("The following courses are registered:");
		return course_registered;
	}
	
	private void viewGradeCard(int studentId)
	{
		
		
		List<StudentGrade> grade_card=null;
		
        grade_card = registrationInterface.viewGradeCard(studentId);
		
        System.out.println("You haven't registered for the following courses:");
	}
	
	private void make_payment(int studentId)
	{
		
		double fee =0.0;
		
        fee=registrationInterface.calculateFee(studentId);
        
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
            notificationInterface.sendNotification(NotificationType.PAYMENT, studentId, mode, fee);
			
		}
		
	}
	
	
}
