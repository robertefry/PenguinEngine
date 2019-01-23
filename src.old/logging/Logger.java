
package logging;

import java.io.PrintStream;

/**
 * class for logging information
 * @author Robert E Fry
 * @date 31 Aug 2018
 * @time 17:33:40
 *
 */
public class Logger {
	
	public static final LogType NULL = new LogType(System.out, "      ");
	public static final LogType INFORM = new LogType(System.out, " INFO ");
	public static final LogType WARNING = new LogType(System.out, " WARN ");
	public static final LogType SEVERE = new LogType(System.err, "SEVERE");
	
	public static boolean print = true;
	
	/**
	 * Log objects as information to the <code>System.out</code> stream
	 * @param objects to log
	 */
	public static void log(Object... objects) {
		log(INFORM, objects);
	}
	
	/**
	 * Log objects to the print stream defined by the <code>Logger.LogType</code> type
	 * @param type the <code>Logger.LogType</code> type of logging
	 * @param objects to log
	 */
	public static void log(LogType type, Object... objects) {

		final StringBuilder builder = new StringBuilder(String.format("[%s] [%s] ", type.getMessage(), TimeStamps.getStamp()));
		final String prespace = String.format("%" + builder.length() + "s", "");
		
		int linenumber = 0;
		for (Object part : objects) for (String line : part.toString().split("\n")) {
			builder.append(String.format("%s%s", (linenumber++ > 0) ? ("\n" + prespace) : "", line));
		}
		
		if (print) type.getPrintStream().println(builder.toString());
		
	}
	
	/**
	 * Definitions for logger message prefixes
	 * @author Robert E Fry
	 * @date 31 Aug 2018
	 * @time 17:33:13
	 *
	 */
	public static class LogType {
		
		private final String message;
		private final PrintStream printStream;
		
		public LogType(PrintStream printStream, String message) {
			this.printStream = printStream;
			this.message = message;
		}
		
		public PrintStream getPrintStream() {
			return printStream;
		}
		
		public String getMessage() {
			return message;
		}
		
	}
	
}
