package com.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		            System.out.println("2. View all student scores sorted in ascending order.");
		            System.out.println("3. Fetch individual student scores using student ID.");
		            System.out.println("4. Identify and view top-scoring students.");
		            System.out.println("5. Logout/Exit");
		            System.out.print("Enter your choice (1-5:) ");

		            choice = sc.nextInt();
		            switch (choice) {
		                case 1 : AddNewQuestions(); 
		                		 break;
		                case 2 : viewAllStudentScores();
		                		 break;
		                case 3 : getStudentScore();
		                		 break;
		                case 4 : viewTopScoringStudents();
		                		 break;
		                case 5 : break;
		            }
		        }while(choice!=5);
    }

	private static void AddNewQuestions() {
		
		String question_text = "";
		String option_a = "";
		String option_b = "";
		String option_c = "";
		String option_d = "";
		String correct_option = "";

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter Question:");
		question_text = sc.nextLine();

		System.out.println("Enter Option A:");
		option_a = sc.nextLine();

		System.out.println("Enter Option B:");
		option_b = sc.nextLine();

		System.out.println("Enter Option C:");
		option_c = sc.nextLine();

		System.out.println("Enter Option D:");
		option_d = sc.nextLine();

		System.out.println("Enter Correct Option (A/B/C/D):");
		correct_option = sc.nextLine();
		
		insertQuestion(question_text,option_a,option_b,option_c,option_d,correct_option
		    );
		
	}
	
	public static void insertQuestion(String questionText,String optionA,String optionB,String optionC,String optionD,String correctOption) {

	    String sql = "INSERT INTO question (question_text, A,B,C,D, correct_answer) VALUES ( ?, ?, ?, ?, ?, ?)";

	    try {
	        Connection con = ConnectionDB.getConnection();

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setString(1, questionText);
	        ps.setString(2, optionA);
	        ps.setString(3, optionB);
	        ps.setString(4, optionC);
	        ps.setString(5, optionD);
	        ps.setString(6, correctOption);

	        int result = ps.executeUpdate();

	        if (result > 0) {
	            System.out.println("Question Inserted Successfully!");
	        }

	        ps.close();
	        con.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void viewAllStudentScores() {
	    String sql = "select * from student,score where student.id=score.student_id order by total_score asc";
	    try {

	        Connection con = ConnectionDB.getConnection();

	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);

	        System.out.println("\n=================================");
	        System.out.println("       Student Scores");
	        System.out.println("=================================");

	        while (rs.next()) {

	            System.out.println("Student ID   : " + rs.getInt("student_id"));
	            
	            System.out.println("Student name : " + rs.getString(2)+" "+rs.getString(3));

	            System.out.println("Score        : " + rs.getInt("total_score"));

	            System.out.println("Grade        : " + rs.getString("grade"));

	            System.out.println("------------------------------------------");
	        }

	        rs.close();
	        stmt.close();
	        con.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void getStudentScore() {

	    Scanner sc = new Scanner(System.in);
	    System.out.print("Enter Student ID: ");
	    int studentId = sc.nextInt();

	    String sql = "SELECT student_id, total_score, grade FROM score WHERE student_id = ?";

	    try {

	        Connection con = ConnectionDB.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, studentId);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {

	            System.out.println("\n=================================");
	            System.out.println("       Student Score");
	            System.out.println("=================================");

	            System.out.println("Student ID : " +rs.getInt("student_id"));

	            System.out.println("Score      : " +rs.getInt("total_score"));

	            System.out.println("Grade      : " +rs.getString("grade"));

	        } else {
	            System.out.println("Student score not found..!!");
	        }

	        rs.close();
	        ps.close();
	        con.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void viewTopScoringStudents() {

	    String sql = "SELECT first_name, last_name, student_id, total_score, grade FROM student,score where student.id=score.student_id ORDER BY total_score DESC";

	    try {

	        Connection con = ConnectionDB.getConnection();

	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);

	        System.out.println("\n=================================");
	        System.out.println("       Top Scoring Students");
	        System.out.println("=================================");

	        int rank = 1;

	        while (rs.next()) {

	            System.out.println("Rank         : " + rank);

	            System.out.println("Student ID   : " + rs.getInt("student_id"));
	            
	            System.out.println("Student Name : " + rs.getString("first_name")+" "+rs.getString("last_name"));

	            System.out.println("Score        : " + rs.getInt("total_score"));

	            System.out.println("Grade        : " + rs.getString("grade"));

	            System.out.println("---------------------------------");

	            rank++;
	        }

	        rs.close();
	        stmt.close();
	        con.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
		
	}



