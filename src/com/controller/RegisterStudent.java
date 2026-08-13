package com.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.Scanner;

import com.connectionDAO.ConnectionDB;

public class RegisterStudent {
	
	public static void registerStudent() {
		
		Student student = new Student();
		Scanner sc = new Scanner(System.in);
		Connection con = null;
		String result = "";
		
		try {
			
			System.out.println("-----Student Registration Form-------");
			System.out.println("Enter Roll Number");
			student.setRoll_no(sc.nextInt());
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
			System.out.println("Enter Email ID");
			student.setEmail_id(sc.next());
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
		
}
