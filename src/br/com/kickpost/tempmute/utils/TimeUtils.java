package br.com.kickpost.tempmute.utils;

import java.util.concurrent.TimeUnit;

public class TimeUtils {

	
	public static  long getTimeByString(String argument) {
		try {
		return argument.length() > 1 ? Long.parseLong(argument.substring(0, argument.length() - 1)) : 0;
		}catch(Exception e) {
			return 0;
		}
	}

	public static  TimeUnit getTimeUnitByString(String argument) {

		if (argument.endsWith("d")) {
			return TimeUnit.DAYS;
		} else if (argument.endsWith("h")) {
			return TimeUnit.HOURS;
		} else if (argument.endsWith("m")) {
			return TimeUnit.MINUTES;
		} else if (argument.endsWith("s")) {
			return TimeUnit.SECONDS;
		} else {
			return null;
		}

	}
	
}
