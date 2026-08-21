package com.controller;

import java.sql.CallableStatement;
import com.validation.Validations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import com.connectionDAO.ConnectionDB;

public class RegisterStudent {
	
	public static void studentLoginAndQuiz() {
		
		
		
		Scanner sc = new Scanner(System.in);
		Connection con = null;
		String userName = "";
		String password = "";
		ResultSet rs = null;
		String quizYN = "N";
		int id = 0 ;
		try {
			con = ConnectionDB.getConnection();
			
			System.out.println("Enter User Name--");
			userName = sc.next();
			System.out.println("Enter Password");
			password = sc.next();
			String sql = "select * from student where username = ? and password = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("Login Successfull...!!");
				id = rs.getInt(1);
				System.out.println("Do you want to Attempt Quiz ? (Y/N)");
				quizYN = sc.next();
			}
			else {
				System.out.println("Invalid Login Credentials...!!");
			}
			
			if(!isQuizAlreadyAttempted(id) && (quizYN.charAt(0)=='Y' || quizYN.charAt(0)=='y')) {
				QuizMaster.startQuiz(id);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void registerStudent() {
		
		Student student = new Student();
		Scanner sc = new Scanner(System.in);
		Connection con = null;
		String result = "";
		
		try {
			int roll_no = 0;
			System.out.println("-----Student Registration Form-------");
			System.out.println("Enter Roll Number");

			while (!sc.hasNextInt()) {
			    System.out.println("Invalid input! Please enter numbers only.");
			    sc.next();
			}

			roll_no = sc.nextInt();

			System.out.println("Roll Number: " + roll_no);
			student.setRoll_no(roll_no);
			System.out.println("Enter First Name");
			student.setFirst_name(sc.next());
			System.out.println("Enter Last Name");
			student.setLast_name(sc.next());
			System.out.println("Enter User Name");
			student.setUser_name(sc.next());
			System.out.println("Enter Password Name");
			student.setPassword(sc.next());
			System.out.println("Enter City");
			student.setCity(sc.next());
			
			String email;
			sc.nextLine();
	        while (true) {
	            System.out.print("Enter Email: ");
	            email = sc.nextLine();
	            if (Validations.isValidEmail(email)) {
	                student.setEmail_id(email);
	                break;
	            } 
	            else {
	                System.out.println("Invalid Email! Please enter again.");
	            }
	        }
			System.out.println("Enter Mobile Number");
			student.setMobile_no(sc.next());
			
			con = ConnectionDB.getConnection();
			
			CallableStatement cs = con.prepareCall("{call register_student(?,?,?,?,?,?,?,?,?)}");
			cs.setInt(1, student.getRoll_no());
			cs.setString(2, student.getFirst_name());
			cs.setString(3, student.getLast_name());
			cs.setString(4, student.getUser_name());
			cs.setString(5, student.getPassword());
			cs.setString(6, student.getCity());
			cs.setString(7, student.getEmail_id());
			cs.setString(8, student.getMobile_no());
			cs.registerOutParameter(9, Types.VARCHAR);
			
			cs.execute();
			
			result = cs.getString(9);
			System.out.println(result);
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
		}
	}
	
	public static void viewMyScore() {

	    String sql = "SELECT total_score, grade FROM score WHERE student_id = ?";
	    int studentId=0;
	    Scanner sc = new Scanner(System.in);
	    
	    try {
	    	
	    	System.out.println("Enter Valid student ID :");
	    	studentId = sc.nextInt();
	    	
	        Connection con = ConnectionDB.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, studentId);
	        ResultSet rs = ps.executeQuery();

	        System.out.println("\n=================================");
	        System.out.println("           MY SCORE");
	        System.out.println("=================================");

	        if (rs.next()) {

	            System.out.println("Student ID : " + studentId);
	            System.out.println("Score      : " + rs.getInt("total_score"));
	            System.out.println("Grade      : " + rs.getString("grade"));

	        } else {

	            System.out.println("No score available/student ID not found");

	        }

	        rs.close();
	        ps.close();
	        con.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static boolean isQuizAlreadyAttempted(int studentId) {

	    String sql = "SELECT student_id FROM score WHERE student_id = ?";

	    try {

	        Connection con = ConnectionDB.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, studentId);
	        ResultSet rs = ps.executeQuery();
	        boolean exists = rs.next();
	        rs.close();
	        ps.close();
	        con.close();

	        return exists;

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return false;
	}
		
}
