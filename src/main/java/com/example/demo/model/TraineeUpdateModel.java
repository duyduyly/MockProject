package com.example.demo.model;

import java.util.Date;
import java.util.List;

import com.example.demo.entity.Trainee;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TraineeUpdateModel {

	private String id;
	private String fullName;
	private String gender;
	private String dateOfBirth;
	private String university;
	private String faculty;
	private String phone;
	private String email;
	private String account;
}
