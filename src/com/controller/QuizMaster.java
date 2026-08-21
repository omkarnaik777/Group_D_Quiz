package com.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.connectionDAO.ConnectionDB;

public class QuizMaster {
	
	public static void startQuiz(int id) {
	    Scanner sc = new Scanner(System.in);
	    String sql = "SELECT * FROM question";
	    int score = 0;
	    int totalQuestions = 0;

	    try {
	        Connection con = ConnectionDB.getConnection();
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);

	        while (rs.next()) {

	            totalQuestions++;

	            System.out.println("\n================================");
	            System.out.println("Question " + totalQuestions);
	            System.out.println("================================");

	            System.out.println(rs.getString("question_text"));

	            System.out.println("A. " + rs.getString("A"));
	            System.out.println("B. " + rs.getString("B"));
	            System.out.println("C. " + rs.getString("C"));
	            System.out.println("D. " + rs.getString("D"));
	            System.out.print("Enter your answer (A/B/C/D): ");
	            String userAnswer = sc.nextLine().toUpperCase();
	            String correctAnswer = rs.getString("correct_answer");
	            if (userAnswer.equals(correctAnswer)) {
	                score++;
	            }
	        }

	        rs.close();
	        stmt.close();

	        String insertScore =
	                "INSERT INTO score (student_id, total_score, grade) VALUES (?, ?, ?)";

	        PreparedStatement ps = con.prepareStatement(insertScore);

	        ps.setInt(1, id);
	        ps.setInt(2, score);

	        String grade;

	        if (score >= 9) {
	            grade = "A+";
	        } else if (score >= 8) {
	            grade = "A";
	        } else if (score >= 6) {
	            grade = "B";
	        } else if (score >= 4) {
	            grade = "C";
	        } else {
	            grade = "D";
	        }

	        ps.setString(3, grade);
	        ps.executeUpdate();
	        ps.close();
	        con.close();

	        System.out.println("\n================================");
	        System.out.println("          QUIZ COMPLETED");
	        System.out.println("================================");

	        System.out.println("Your Score : " + score + "/" + totalQuestions);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
