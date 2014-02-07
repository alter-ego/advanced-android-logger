package com.alterego.advancedandroidlogger.implementations;

import com.alterego.advancedandroidlogger.interfaces.ILogger;

/**
 * NullAndroidLogger should be used when declaring ILogger in the app (to avoid NullPointerExceptions when
 * calling uninstantiated ILogger (i.e. calling ILogger before we have a contest for instantiating)
 */
public class NullAndroidLogger implements ILogger {

	public static final ILogger instance = new NullAndroidLogger();
	
	private NullAndroidLogger () {
	}
	
	@Override
	public void verbose(String tag, String content) {
	}

	@Override
	public void verbose(String content) {
	}

	@Override
	public void debug(String tag, String content) {
	}

	@Override
	public void debug(String content) {
	}

	@Override
	public void info(String tag, String content) {
	}

	@Override
	public void info(String content) {
	}

	@Override
	public void warning(String tag, String content) {
	}

	@Override
	public void warning(String content) {
	}

	@Override
	public void error(String tag, String content) {
	}

	@Override
	public void error(String content) {
	}

	@Override
	public void fail(String tag, String content) {
	}

	@Override
	public void fail(String content) {
	}

	@Override
	public void setLoggingLevel(LoggingLevel level) {
	}

	@Override
	public ILogger getLogger(Object instance) {
		return this;
	}

}
