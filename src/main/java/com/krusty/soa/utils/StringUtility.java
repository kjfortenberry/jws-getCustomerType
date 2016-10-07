package com.krusty.soa.utils;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;

/**
 * Provides utility methods for String objects.
 */
public final class StringUtility {
	
	private static final String COLON = ":";

	/**
	 * private constructor to prevent initialization
	 */
	private StringUtility() {
	}

	/**
	 * Formats given message by replacing dynamic parameters with dynamic values.
	 * @param msg
	 * @param dynValues
	 * @return String
	 */
	public static String formatMessage(String msg, Object[] dynValues) {
		if (StringUtils.isNotBlank(msg) && null != dynValues) {
			Object[] substValues = dynValues;

			// Supply dummy values if we have too few values for the message pattern.
			MessageFormat mf = new MessageFormat(msg);
			Format[] formats = mf.getFormatsByArgumentIndex();

			// Do we have more formats that we have values to fill them?
			if (formats.length > dynValues.length) {
				// Yes, so create new 'value' array with the correct number of values
				substValues = new Object[formats.length];
				// copy in the values passed to us
				System.arraycopy(dynValues, 0, substValues, 0, dynValues.length);
				// create blank values (empty strings) as default values
				for (int i = dynValues.length; i < substValues.length; i++) {
					substValues[i] = "";
				}
			}
			return MessageFormat.format(msg, substValues);
		}
		return msg;
	}

	/**
	 * Converts given list into String.  List Object must contain only Strings
	 * else it will throw a ClassCastException.  If arguments are null, 
	 * IllegalArgumentException will be thrown.
	 * @param list
	 * @return String
	 */
	public static String asString(List<String> list) {
		if (null == list || list.isEmpty()) {
			throw new IllegalArgumentException("List cannot be null or empty.");
		}

		StringBuilder sb = new StringBuilder();
		ListIterator<String> li = list.listIterator();
		while (li.hasNext()) {
			sb.append(li.next());
		}

		return sb.toString();
	}

	/**
	 * Converts given list into String.  List Object must contain only Strings
	 * else it will throw a ClassCastException.  Return String will be 
	 * delimited with supplied delimiter.  If arguments are null, 
	 * IllegalArgumentException will be thrown.
	 * @param list
	 * @param delimeter
	 * @return String
	 */
	public static String asString(List<String> list, char delimeter) {
		if (null == list || list.isEmpty()) {
			throw new IllegalArgumentException("List cannot be null or empty.");
		}

		StringBuilder sb = new StringBuilder();
		ListIterator<String> li = list.listIterator();
		int i = 0;
		while (li.hasNext()) {
			if (0 != i) {
				sb.append(delimeter);
			}
			sb.append(li.next());
			i++;
		}

		return sb.toString();
	}

	public static String formatPhone(int areaCode, int exchange, int number) {
		// Defect 724 - for phone numbers which do not exist, return null.
		if ((areaCode == 0) && (exchange == 0) && (number == 0)) {
			return null;
		}
		Formatter formatter = new Formatter();
		formatter.format("%03d%03d%04d", areaCode, exchange, number);

		try {
		    return formatter.toString();
		} finally {
		    if (formatter != null) {
		        formatter.close();
		    }
		}
	}

	/**
	 * Splits a String into small tokens and size of each token will be passed length.  
	 * If an empty string is passed, it returns null.  After each token is extracted, 
	 * it will be trimmed again<br>  
	 * 
	 * Examples:<br> 
	 * 
	 * Example1: If passed String is "HelloWorld" and length is 3, return list will
	 * contains "hel", "low", "orl" and "d". 
	 *
	 * Example2: If passed String is "Hello World " and length is 5, return list will
	 * contains "hello", "world".  (space infront of 'W' gets deleted).
	 *    
	 * If passed String have any trailing or leading spaces they will be removed.
	 * 
	 * @param s
	 * @param length
	 * @return
	 */
	public static List<String> tokenize(String s, int length) {
		if (StringUtils.isBlank(s) || length <= 0) {
			return null;
		}

		List<String> returnList = new ArrayList<String>(5);

		String trimmedString = s.trim(); //remove any spaces
		int trimmedStringLength = trimmedString.length();
		while (trimmedStringLength > length) {
			String token = trimmedString.substring(0, length).trim();
			trimmedString = trimmedString.substring(length).trim();
			trimmedStringLength = trimmedString.length();
			returnList.add(token);
		}

		//make sure any chars are still left.  If left, treat that as one token
		if (trimmedStringLength != 0 && StringUtils.isNotBlank(trimmedString)) {
			returnList.add(trimmedString);
		}

		return returnList;
	}

	/**
	 * Formats Icoms time.  Icoms stores time values in 4 characters in the format of 
	 * hour and minute.  Based on the length, passed value will be formatted as 
	 * hour:minute by padding with zeros.
	 * @param time
	 * @return
	 */
	public static String formatTime(String time) {
		if (StringUtils.isBlank(time)) {
			return time;
		}

		String trimmedTime = time.trim();

		if (trimmedTime.length() == 4) {
			return trimmedTime.substring(0, 2) + COLON + trimmedTime.substring(2);
		} else if (trimmedTime.length() == 3) {
			return "0" + trimmedTime.substring(0, 1) + COLON + trimmedTime.substring(1);
		} else if (trimmedTime.length() == 2) {
			return "00" + COLON + trimmedTime;
		} else if (trimmedTime.length() == 1) {
			return "00" + COLON + "0" + trimmedTime;
		} else { //should never come here
			return time;
		}
	}

	/**
	 * Formats a number value into a proper dollar value String,
	 * @param number value
	 * @return properly formatted dollar value
	 */
	public static String formatDollars(String dollars) {

		if (StringUtils.isBlank(dollars)) {
			return null;
		}

		return formatDollars(Double.parseDouble(dollars));
	}

	/**
	 * Formats a dollar number into a String,
	 * @param dollar value
	 * @return dollar value as a string
	 */
	public static String formatDollars(double dollars) {

		DecimalFormat df = new DecimalFormat("#.##");
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);

		return df.format(dollars);
	}

	/**
	 * Tokenizes given string into a list using the given delimiter.
	 * 
	 * @param s
	 * @param delimeter
	 * @return
	 */
	public static List<String> asList(String s, String delimeter) {
		if (StringUtils.isBlank(s)) {
			return null;
		}

		StringTokenizer st = new StringTokenizer(s, delimeter);
		List<String> list = new ArrayList<String>(st.countTokens());
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}
		return list;
	}
}
