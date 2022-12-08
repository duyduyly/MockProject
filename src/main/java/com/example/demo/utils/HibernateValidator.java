package com.example.demo.utils;

import java.util.Objects;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.example.demo.entity.BaseEntity;

public class HibernateValidator {

	public static <T extends BaseEntity> boolean validateEntity(T obj) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
		System.out.println("Validating " + obj.toString());
		if (Objects.isNull(constraintViolations) || constraintViolations.size() == 0) {
			System.out.println(obj.toString() + " is valid");
			return true;
		} else {
			for (ConstraintViolation<T> constraintViolation : constraintViolations) {
				System.out.println(constraintViolation.getMessage());
			}
			return false;
		}
	}
	
	public static <T extends BaseEntity> String validateEntityAndGetMessage(T obj) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
		System.out.println("Validating " + obj.toString());
		if (Objects.isNull(constraintViolations) || constraintViolations.size() == 0) {
			System.out.println(obj.toString() + " is valid");
			return null;
		} else {
			for (ConstraintViolation<T> constraintViolation : constraintViolations) {
				System.out.println(constraintViolation.getMessage());
				return constraintViolation.getMessage();
			}
		}
		return null;
	}

}
