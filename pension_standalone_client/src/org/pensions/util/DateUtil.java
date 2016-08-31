package org.pensions.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateUtil {

	public static Date stringToDate(String dateInString) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//String dateInString = "07/06/2013";
		Date date = null;
		try {
			date = formatter.parse(dateInString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}
	
	public static LocalDate stringToLocalDate(String dateInString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//String dateInString = "07/06/2013";
		LocalDate date = null;
		try {
			date = LocalDate.parse(dateInString, formatter);
		} catch (DateTimeParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}

	
	public static String dateToString(LocalDate dateInDate) {
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//String dateInString = "07/06/2013";
		String date = null;
		try {
			date = dateInDate.format(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return date;
	}
}
