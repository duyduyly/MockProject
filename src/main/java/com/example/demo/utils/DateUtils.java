package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;


//PARSE JAVA OBJECT USING formateDate TO GET yyyy-MM-dd
public class DateUtils {
	
	public static String getCurrrentDateTime() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dateTimeFormatter.format(now).toString();
	}
	
	public static Date parseDateFromString(String dateString) {
		String pattern = "yyyy-MM-dd";
//		String pattern = "ddd-MMM-YYYY HH:mm aa";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = simpleDateFormat.parse(dateString);
			return date;
		} catch (ParseException e) {
			System.out.println("Cannot parse date");
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date parseDDMMYYYYDateFromString(String dateString) {
		String pattern = "dd/MM/yyyy";
//		String pattern = "ddd-MMM-YYYY HH:mm aa";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = simpleDateFormat.parse(dateString);
			return date;
		} catch (ParseException e) {
			System.out.println("Cannot parse date");
			e.printStackTrace();
			return null;
		}
	}
	
	public static String formatDate(Date inputDate) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try {
			String result  = simpleDateFormat.format(inputDate);
			return result;
		} catch (Exception e) {
			System.out.println("Cannot parse date");
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date correctDateFormat(Date inputDate) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			String result  = simpleDateFormat.format(inputDate);
			System.out.println(result);
			date = parseDateFromString(result);
			return date;
		} catch (Exception e) {
			System.out.println("Cannot parse date");
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date addOrSubtractDate(Date inputDate, Integer calculateRange) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(inputDate);
		calendar.add(Calendar.DATE, calculateRange);
		date = calendar.getTime();
		return date;
	}
}
