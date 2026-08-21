package com.controller;

import java.util.Scanner;

public class Main_Quiz {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice;
		        do{
		            System.out.println("\n=================================");
		            System.out.println("   WELCOME TO QUIZMASTER SYSTEM  ");
		            System.out.println("=================================");
		            System.out.println("1. Register Student");
		            System.out.println("2. Student Login & Attempt Quiz");
		            System.out.println("3. View My Score");
		            System.out.println("4. Admin Login");
		            System.out.println("5. Exit");
		            System.out.print("Enter your choice (1-5): ");

		            choice = sc.nextInt();
		            switch (choice) {
		                case 1 : RegisterStudent.registerStudent(); 
		                		 break;//registerStudent();
		                case 2 : RegisterStudent.studentLoginAndQuiz();
		                		 break; //studentLoginAndQuiz();
		                case 3 : RegisterStudent.viewMyScore();
		                	 	 break;
		                case 4 : Admin.AdminLogin(); 
		                		 break;
		                case 5 : break;
		            }
		        }while(choice!=5);
    }
}


