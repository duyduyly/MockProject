package com.example.demo.service.iservice;

import java.util.List;

import com.example.demo.entity.BaseEntity;

public interface IPersonService {

	<T extends BaseEntity> void generateAccount(T person);

	String generateAccountFromName(String inputString);

	Long checkExistedAccount(String account);

	<T extends BaseEntity> boolean checkIfPersonExisted(T person);

	<T extends BaseEntity> boolean checkIfPersonInListExisted(List<T> listOfPersons);
}
