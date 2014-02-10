package com.alterego.advancedandroidlogger.implementations;

import android.util.Log;

import com.alterego.advancedandroidlogger.interfaces.IAndroidLogger;

/**
 * This is the wrapper around the basic Android {@link Log} logger. It has logging methods in two flavours, with tag and without 
 * (default tag is "ADVANCEDANDROIDLOGGER") - the tag may also be set via constructor, but can also be overridden.
 * {@link setLoggingLevel} method sets the logging level, below which log calls will not
 * be logged (e.g. if you set logging level to NORMAL, all the ILogger calls to verbose and debug methods will
 * not result in printed logs); default logging level is NORMAL i.e. INFO.
 */
public class AndroidLogger implements IAndroidLogger {

	private int mLoggingLevel = 2;
	private String mLoggingTag = "ADVANCEDANDROIDLOGGER";

	/**
	 * Initializes the AndroidLogger with the default tag "LOGGER" and the 
	 * default debugging level NORMAL (Log.i).
	 *
	 * @param tag Logging tag
	 */
	public AndroidLogger() {
		this(null, LoggingLevel.NORMAL);
	}

	/**
	 * Initializes the AndroidLogger with a specified tag and the 
	 * default debugging level NORMAL (Log.i).
	 *
	 * @param tag Logging tag
	 */

	public AndroidLogger(String tag) {
		this(tag, LoggingLevel.NORMAL);
	}

	/**
	 * Initializes the AndroidLogger with a specified tag and a specified logging
	 * level.
	 *
	 * @param tag   Logging tag
	 * @param level Logging level {@link IAndroidLogger.LoggingLevel}
	 */

	public AndroidLogger(String tag, LoggingLevel level) {
		if (tag!=null)
			mLoggingTag = tag;
		setLoggingLevel(level);
	}


	@Override
	public void verbose(String tag, String content) {
		if (mLoggingLevel == 0)
			Log.v(tag, content);
	}

	@Override
	public void verbose(String content) {
		verbose(mLoggingTag, content);
	}

	@Override
	public void debug(String tag, String content) {
		if (mLoggingLevel <= 1)
			Log.d(tag, content);
	}

	@Override
	public void debug(String content) {
		debug(mLoggingTag, content);
	}

	@Override
	public void info(String tag, String content) {
		if (mLoggingLevel <= 2)
			Log.i(tag, content);
	}

	@Override
	public void info(String content) {
		info(mLoggingTag, content);
	}

	@Override
	public void warning(String tag, String content) {
		Log.w(tag, content);
	}

	@Override
	public void warning(String content) {
		warning(mLoggingTag, content);
	}

	@Override
	public void error(String tag, String content) {
		Log.e(tag, content);
	}

	@Override
	public void error(String content) {
		error(mLoggingTag, content);
	}

	@Override
	public void fail(String tag, String content) {
		Log.wtf(tag, content);
	}

	@Override
	public void fail(String content) {
		fail(mLoggingTag, content);
	}	

	@Override
	public IAndroidLogger getLogger(Object instance) {
		return this;
	}
	
	@Override
	public void setLoggingLevel(LoggingLevel level) {

		if (level == LoggingLevel.VERBOSE)
			mLoggingLevel = 0;
		else if (level == LoggingLevel.DEBUG)
			mLoggingLevel = 1;
		else if (level == LoggingLevel.NORMAL)
			mLoggingLevel = 2;
		else
			mLoggingLevel  = 3;

	}
	
	@Override
	public void setLoggingLevel (int level) {
		mLoggingLevel = level;
	}
	
	@Override
	public int getLoggingLevel() {
		return mLoggingLevel;
	}

	@Override
	public String getLoggingTag(Object instance) {
		return mLoggingTag;
	}

}
