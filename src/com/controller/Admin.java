package com.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.connectionDAO.ConnectionDB;

public class Admin {
	
	public static void AdminLogin() {
		
		Scanner sc = new Scanner(System.in);
		Connection con = null;
		String userName = "";
		String password = "";
		ResultSet rs = null;
		
		try {
			con = ConnectionDB.getConnection();
			System.out.println("Enter User Name--");
			userName = sc.next();
			System.out.println("Enter Password");
			password = sc.next();
			
			String sql = "select * from teacher_info where user_name = ? and pass = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("Login Successfull...!!");
				AdminDashboard();
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	private static void AdminDashboard() {
		
		Scanner sc = new Scanner(System.in);
		int choice;
		        do{
		            System.out.println("\n=================================");
		            System.out.println("   Admin Dashboard ");
		            System.out.println("=================================");
		            
		           
		            
		            
		            
		            
		            
		            
		            System.out.println("1. Add new quiz questions with four options and correct answers.");
		            System.out.println("2. Edit or delete existing questions from the quiz database.");
		            System.out.println("3. View all student scores sorted in ascending order.");
		            System.out.println("4. Fetch individual student scores using student ID.");
		            System.out.println("5. Identify and view top-scoring students.");
		            System.out.println("6. Logout/Exit");
		            System.out.print("Enter your choice (1-6): ");

		            choice = sc.nextInt();
		            switch (choice) {
		                case 1 : AddNewQuestions(); 
		                		 break;
		                case 2 : 
		                		 break;
		                case 3 : break;
		                case 4 : break;
		                case 5 : break;
		            }
		        }while(choice!=5);
    }

	private static void AddNewQuestions() {
		
		String question_text = "";
		
		
	}
		
	}



