package com.example.demo.utils;

import java.util.List;

public class StringValidateUtils {
	
	public static boolean hasBadString(List<String> listOfStrings) {
		
		for (String string : listOfStrings) {
			if (string.isEmpty() || string.isBlank()) {
				System.out.println("String not valid");
				return true;
			}
		}
		System.out.println("Strings are valid.");
		return false;
	}

}
