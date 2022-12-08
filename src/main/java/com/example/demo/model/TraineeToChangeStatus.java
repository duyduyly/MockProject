package com.example.demo.model;

import java.util.List;

import com.example.demo.entity.Trainee;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TraineeToChangeStatus {
	
	private List<Trainee> listOfTrainees;

	public TraineeToChangeStatus(List<Trainee> listOfTrainees) {
		super();
		this.listOfTrainees = listOfTrainees;
	}
	
	public void setTrainees( List<Trainee> listOfTrainees) {
		this.listOfTrainees = listOfTrainees;
	}

}
