package com.krusty.soa.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;

/**
* Utility class 
*/
public final class Utility {
	/**
	 * Logger object for message logging.
	 */
	private static final Logger LOGGER = Logger.getLogger(Utility.class);

	/**
	 * private constructor to prevent any initialization with constructors.
	 * User should never instantiate this class.
	 *
	 */
	private Utility() {
		super();
	}

	/**
	 * Closes InputStream object.  If any exception occurs during close, 
	 * it will logged as WARN.
	 * @param stream
	 */
	public static void close(InputStream stream) {
		try {
			if (null != stream) {
				stream.close(); //closes input stream
			}
		} catch (IOException ie) {
			LOGGER.warn("Exception while closing input stream.", ie);
		}
	}

	/**
	 * Closes <code>OutputStream</code> object.  If any exception occurs 
	 * during close, it will logged as WARN.
	 * @param stream
	 */
	public static void close(OutputStream stream) {
		try {
			if (null != stream) {
				stream.close(); //closes input stream
			}
		} catch (IOException ie) {
			LOGGER.warn("Exception while closing output stream.", ie);
		}
	}

	/**
	 * Closes Reader object.  If any exception occurs during close, 
	 * it will logged as WARN.
	 * @param reader Reader
	 */
	public static void close(Reader reader) {
		try {
			if (null != reader) {
				reader.close(); //closes underlying reader
			}
		} catch (IOException ie) {
			LOGGER.warn("Exception while closing Reader.", ie);
		}
	}

	/**
	 * Closes Writer object.  If any exception occurs during close, 
	 * it will logged as WARN.
	 * @param writer
	 */
	public static void close(Writer writer) {
		try {
			if (null != writer) {
				writer.close(); //closes underlying writer
			}
		} catch (IOException ie) {
			LOGGER.warn("Exception while closing writer.", ie);
		}
	}

	/**
	 * Returns the String representation of the passed object. 
	 * @param object
	 * @return String
	 */
	public static String toString(Object object) {
		if (null == object) {
			return null;
		}

		if (object instanceof String) {
			return (String) object;
		}

		Class<?> objClass = object.getClass();
		StringBuilder sb = new StringBuilder();
		sb.append('[').append(objClass.getName()).append("::").append(System.getProperty("line.separator"));
		Field[] fields = objClass.getDeclaredFields();

		// an enum instance contains a list of ALL its enums.
		// if this is an enum, ignore the list of ALL of its
		// enums while displaying fields for the object.
		// Otherwise we get into a recursive toString() call.
		boolean isEnumInstance = object.getClass().isEnum();
		try {
			Field field = null;
			boolean fieldProcessed = false;

			for (int i = 0; i < fields.length; i++) {
				field = fields[i];
				field.setAccessible(true);
				Object objValue = field.get(object);

				// ignore list of enums within an enum instance
				if (isEnumInstance) {
					if (objValue != null) {
						if (objClass.equals(objValue.getClass())) {
							continue;
						}
					}
				}
				// ignore Serializable versions
				if (field.getName().equals("serialVersionUID")) continue;
				if (fieldProcessed) {
					sb.append(',');
				}
				sb.append(field.getName()).append('=').append(objValue);

				fieldProcessed = true;
			}
		} catch (IllegalAccessException ie) {
			LOGGER.error("Cannot print this objects declared fields and their values.", ie);
		}

		sb.append(']');
		return sb.toString();
	}

	/**
	 * Converts stack trace to String
	 * @param th
	 * @return String
	 */
	public static String toString(Throwable th) {
		StringWriter sw = null;
		PrintWriter pw = null;
		try {
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			th.printStackTrace(pw);
			return sw.toString();
		} finally {
			close(pw);
			close(sw);
		}

	}

	public static String listToString(List<?> list, String sep) {
		StringBuffer sb = new StringBuffer();
		for (Object obj : list) {
			if (sb.length() > 0) {
				sb.append(sep);
			}
			sb.append(obj.toString());
		}
		return sb.toString();
	}



	/**
	 * Converts a generic Object to an int value. The 'value add' of this method
	 * is that it checks to see if the Object is null (to avoid NullPointerException 
	 * issues). If the Object is null or cannot be converted to an int then 
	 * a zero value is returned. 
	 * 
	 * @param obj Any object whose String representation is an integer.
	 * @return  Either a int or 0 (zero) if the object could not be
	 * 			converted.
	 */
	public static int toInt(Object obj) {
		return toInt(obj, 0);
	}

	/**
	 * Converts a generic Object to an int value. The 'value add' of this method
	 * is that it checks to see if the Object is null (to avoid NullPointerException 
	 * issues). If the Object is null or cannot be converted to an int then 
	 * the defaultValue value is returned. 
	 * 
	 * @param obj Any object whose String representation is an integer.
	 * @return  Either a int or the defaultValue if the object could not be
	 * 			converted.
	 */
	public static int toInt(Object obj, int defaultValue) {
		int rtrnVal = 0;

		if (obj == null) {
			rtrnVal = defaultValue;
		} else {
			if (obj instanceof BigInteger) {
				rtrnVal = ((BigInteger) obj).intValue();
			} else if (obj instanceof Integer) {
				rtrnVal = ((Integer) obj).intValue();
			} else {
				rtrnVal = NumberUtils.toInt(obj.toString(), defaultValue);
			}
		}

		return rtrnVal;
	}

	/**
	 * Converts a generic Object into a BigInteger. The object is first
	 * converted to its String representation which is then passed to the
	 * BigInteger(String) constructor. If the String representation of the 
	 * object cannot be converted to a BigInteger then null is returned.
	 * 
	 * @param obj Any object whose String representation is a BigInteger.
	 * @return  Either a BigInteger or null if the object could not be
	 * 			converted.
	 */
	public static BigInteger toBigInteger(Object obj) {
		BigInteger rtrnVal = null;

		if (obj != null) {
			try {
				rtrnVal = new BigInteger(obj.toString());
			} catch (NumberFormatException ignored) {
				// do nothing
			}
		}

		return rtrnVal;
	}

	/**
	 * Converts an InputStream to a String
	 * @param stream
	 * @return contents of stream, as a String
	 * @throws IOException
	 */
	public static String streamToString(InputStream stream) throws IOException {
		StringBuffer result = new StringBuffer();

		int bytesRead = 0;
		byte[] bytes = new byte[10000];
		while ((bytesRead = stream.read(bytes)) != -1) {
			result.append(new String(bytes, 0, bytesRead));
		}

		return result.toString();
	}


	/**
	 * Utility function to convert a clob into string
	 * @param clob
	 * @return
	 */
	public static String clobToString(Clob clob) {

		if (clob == null) {
			return null;
		}

		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(clob.getCharacterStream());
			String line = null;

			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			return sb.toString();
		} catch (SQLException se) {
			throw new RuntimeException( "SQLException: " + se.getMessage() );
		} catch (IOException ie) {
			throw new RuntimeException( "SQLException: " + ie.getMessage() );
		}
	}
}
