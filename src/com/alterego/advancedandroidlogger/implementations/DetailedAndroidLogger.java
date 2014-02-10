package com.alterego.advancedandroidlogger.implementations;

import com.alterego.advancedandroidlogger.interfaces.IAndroidLogger;

/**
 * This is the wrapper around {@link AndroidLogger} Android logger that adds additional class, method and line info 
 * to the logging text. It is in the following format:
 * className.methodName() @ line lineNr ": " + normal logger content;
 */

public class DetailedAndroidLogger implements IAndroidLogger {

	private IAndroidLogger mLogger;

	/**
	 * Initializes the DetailedAndroidLogger with the default tag "LOGGER" and the 
	 * default debugging level NORMAL (Log.i).
	 *
	 * @param tag Logging tag
	 */
	public DetailedAndroidLogger() {
		this(null, LoggingLevel.NORMAL);
	}

	/**
	 * Initializes the DetailedAndroidLogger with a specified tag and the 
	 * default debugging level NORMAL (Log.i).
	 *
	 * @param tag Logging tag
	 */

	public DetailedAndroidLogger(String tag) {
		this(tag, LoggingLevel.NORMAL);
	}

	/**
	 * Initializes the DetailedAndroidLogger with a specified tag and a specified logging
	 * level.
	 *
	 * @param tag   Logging tag
	 * @param level Logging level {@link IAndroidLogger}
	 */

	public DetailedAndroidLogger(String tag, LoggingLevel level) {
		mLogger = new AndroidLogger (tag, level);
	}

	private String getDetails (String content) {
		String details = "";

		try {
			String fullClassName = Thread.currentThread().getStackTrace()[4].getClassName();
			String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
			String methodName = Thread.currentThread().getStackTrace()[4].getMethodName();
			int lineNumber = Thread.currentThread().getStackTrace()[4].getLineNumber();
			details = className + "." + methodName + "() @ line " + lineNumber + ": " + content;
		} catch (Exception e) {
			mLogger.warning("LOGGER", "DetailedAndroidLogger.getDetails() couldn't get details because of " + e.getMessage());
			e.printStackTrace();
		}

		return details;

	}

	@Override
	public void verbose(String tag, String content) {
		mLogger.verbose(tag, getDetails(content));
	}

	@Override
	public void verbose(String content) {
		mLogger.verbose(getDetails(content));
	}

	@Override
	public void debug(String tag, String content) {
		mLogger.debug(tag, getDetails(content));
	}

	@Override
	public void debug(String content) {
		mLogger.debug(getDetails(content));
	}

	@Override
	public void info(String tag, String content) {
		mLogger.info(tag, getDetails(content));
	}

	@Override
	public void info(String content) {
		mLogger.info(getDetails(content));
	}

	@Override
	public void warning(String tag, String content) {
		mLogger.warning(tag, getDetails(content));
	}

	@Override
	public void warning(String content) {
		mLogger.warning(getDetails(content));
	}

	@Override
	public void error(String tag, String content) {
		mLogger.error(tag, getDetails(content));
	}

	@Override
	public void error(String content) {
		mLogger.error(getDetails(content));
	}

	@Override
	public void fail(String tag, String content) {
		mLogger.fail(tag, getDetails(content));
	}

	@Override
	public void fail(String content) {
		mLogger.fail(getDetails(content));
	}

	@Override
	public void setLoggingLevel(LoggingLevel level) {
		mLogger.setLoggingLevel(level);
	}

	@Override
	public IAndroidLogger getLogger(Object instance) {
		return this;
	}

	@Override
	public String getLoggingTag(Object instance) {
		return mLogger.getLoggingTag(instance);
	}

	@Override
	public void setLoggingLevel(int level) {
		mLogger.setLoggingLevel(level);
		
	}

	@Override
	public int getLoggingLevel() {
		return mLogger.getLoggingLevel();
	}

}
