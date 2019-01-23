
package logging;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Static class for generating time stamps
 * @author Robert E Fry
 * @date 31 Aug 2018
 * @time 17:31:14
 *
 */
public class TimeStamps {
	
	protected static final Date date = new Date();
	protected static final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	
	/**
	 * Applies the given pattern string to the date format.
	 * @param pattern
	 */
	public static void applyPattern(final String pattern) {
		format.applyPattern(pattern);
	}
	
	/**
	 * Calculates the time/date stamp as formatted by {@code format}
	 * @return the time/date stamp
	 */
	public static String getStamp() {
		date.setTime(System.currentTimeMillis());
		return format.format(date);
	}
	
}
