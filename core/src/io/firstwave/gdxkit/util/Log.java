package io.firstwave.gdxkit.util;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

/**
 * Simple wrapper class over Gdx.app.* logging methods. Will fallback to System.out.println if Gdx.app has not been initialized yet.
 */
public class Log {

	private static int sLogLevel = Application.LOG_INFO;

	private static final Object[] EMPTY_FORMAT_ARGS = new Object[0];
	private static final String UNTAGGED = "Untagged";
	private static String sDefaultTag;
	
	private static final String STDOUT_FMT = "%s: %s\t%s";
	private static final String[] LEVEL_TO_TEXT = new String[] {
		"VERBOSE", "DEBUG", "INFO", "ERROR"
	};
	
	private static boolean sIsInit = false;
	
	public static void setLogLevel(int level) {
		checkInit();
		sLogLevel = level;
	}
	
	public static int getLogLevel() {
		return sLogLevel;
	}
	
	public static void setDefaultTag(String defaultTag) {
		sDefaultTag = defaultTag;
	}
	
	private static String constructMessage(String message, Object... formatArgs) {
		try {
			return String.format(message, formatArgs);
		} catch (Exception e) {
			return message;
		}
	}
	
	private static void checkInit() {
		if (sIsInit) {
			return;
		}
		if (Gdx.app == null) {
			return;
		}
		Gdx.app.setLogLevel(sLogLevel);
		sIsInit = true;
	}

	public static void out(int level, String tag, String message, Throwable exception, Object[] formatArgs) {
		if (level < sLogLevel) {
			return;
		}
		
		if (level < Application.LOG_DEBUG || level > Application.LOG_ERROR) {
			Throwable t = new IllegalArgumentException("Invalid log level:" + level);
			out(Application.LOG_ERROR, tag, message, t, EMPTY_FORMAT_ARGS);
			return;
		}
		
		if (tag == null || "".equals(tag)) {
			tag = UNTAGGED;
		}
		
		String fmtMsg = constructMessage(message, formatArgs);
		
		if (Gdx.app == null) {
			System.out.println(String.format(STDOUT_FMT, LEVEL_TO_TEXT[level], tag, fmtMsg));
			if (exception != null) {
				exception.printStackTrace();
			}
		} else {
			checkInit();
			switch (level) {
				case Application.LOG_DEBUG:
					if (exception == null) {
						Gdx.app.debug(tag, fmtMsg);
					} else {
						Gdx.app.debug(tag, fmtMsg, exception);
					}
					break;
				case Application.LOG_INFO:
					if (exception == null) {
						Gdx.app.log(tag, fmtMsg);
					} else {
						Gdx.app.log(tag, fmtMsg, exception);
					}
					break;
				case Application.LOG_ERROR:
					if (exception == null) {
						Gdx.app.error(tag, fmtMsg);
					} else {
						Gdx.app.error(tag, fmtMsg, exception);
					}
					break;
				default:
			}
		}
	}

	public static void d(String tag, String message) {
		out(Application.LOG_DEBUG, tag, message, null, EMPTY_FORMAT_ARGS);
	}
	
	public static void d(String tag, String message, Throwable exception) {
		out(Application.LOG_DEBUG, tag, message, exception, EMPTY_FORMAT_ARGS);
	}
	
	public static void d(String tag, String message, Object... formatArgs) {
		out(Application.LOG_DEBUG, tag, message, null, formatArgs);
	}
	
	public static void d(Object object) {
		out(Application.LOG_DEBUG, null, String.valueOf(object), null, EMPTY_FORMAT_ARGS);
	}

	public static void i(String tag, String message) {
		out(Application.LOG_INFO, tag, message, null, EMPTY_FORMAT_ARGS);
	}

	public static void i(String tag, String message, Throwable exception) {
		out(Application.LOG_INFO, tag, message, exception, EMPTY_FORMAT_ARGS);
	}

	public static void i(String tag, String message, Object... formatArgs) {
		out(Application.LOG_INFO, tag, message, null, formatArgs);
	}

	public static void i(Object object) {
		out(Application.LOG_INFO, null, String.valueOf(object), null, EMPTY_FORMAT_ARGS);
	}

	public static void e(String tag, String message) {
		out(Application.LOG_ERROR, tag, message, null, EMPTY_FORMAT_ARGS);
	}

	public static void e(String tag, String message, Throwable exception) {
		out(Application.LOG_ERROR, tag, message, exception, EMPTY_FORMAT_ARGS);
	}

	public static void e(String tag, String message, Object... formatArgs) {
		out(Application.LOG_ERROR, tag, message, null, formatArgs);
	}

	public static void e(Object object) {
		out(Application.LOG_ERROR, null, String.valueOf(object), null, EMPTY_FORMAT_ARGS);
	}
	
}
