package com.comcast.crm.generic.webdriverutility;

import java.time.LocalDate;
import java.util.Random;

public class JavaUtility {
	public int getRandomNumber() {
		Random r1 = new Random();
		return r1.nextInt(5000);
	}
	
	public String getCurrentDateDDMMYYYY() {
		LocalDate localDate = LocalDate.now();
//		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
//		String date = dateTimeFormatter.format(localDate);
		return localDate.toString();
	}
	
	public String getPostMonthDateDDMMYYYY(int months) {
		LocalDate localDate = LocalDate.now().plusMonths(months);
//		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
//		String date = dateTimeFormatter.format(localDate);
		return localDate.toString();
	}
	
	public String getDateWithAddingDaysDDMMYYYY(int days) {
		LocalDate localDate = LocalDate.now().plusDays(days);
//		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
//		String date = dateTimeFormatter.format(localDate);
		return localDate.toString();
	}
}
