package com.controller;

public class Student {
	
	private int roll_no;
	private String first_name;
	private String last_name;
	private String user_name;
	private String password;
	private String city;
	private String email_id;
	private String mobile_no;
	
	public int getRoll_no() {
		return roll_no;
	}
	public void setRoll_no(int roll_no) {
		this.roll_no = roll_no;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	@Override
	public String toString() {
		return "Student [roll_no=" + roll_no + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", user_name=" + user_name + ", password=" + password + ", city=" + city + ", email_id=" + email_id
				+ ", mobile_no=" + mobile_no + "]";
	}
}
