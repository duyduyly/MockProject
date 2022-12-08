package com.example.demo.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TraineeForm {

	private String EmplID;
	private String StringName;

//	@DateTimeFormat(pattern = "dd-MM-YYYY")
	private String DOB;
	private String Gender;
	private String University;
	private String Faculty;
	private String Phone;
	private String Email;
	private String Status;

	public TraineeForm(String emplID, String stringName, String dOB, String gender, String university, String faculty,
			String phone, String email, String status) {
		super();
		EmplID = emplID;
		StringName = stringName;
		DOB = dOB;
		Gender = gender;
		University = university;
		Faculty = faculty;
		Phone = phone;
		Email = email;
		Status = status;
	}

	public String getEmplID() {
		return EmplID;
	}

	public void setEmplID(String emplID) {
		EmplID = emplID;
	}

	public String getStringName() {
		return StringName;
	}

	public void setStringName(String stringName) {
		StringName = stringName;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getUniversity() {
		return University;
	}

	public void setUniversity(String university) {
		University = university;
	}

	public String getFaculty() {
		return Faculty;
	}

	public void setFaculty(String faculty) {
		Faculty = faculty;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

}
