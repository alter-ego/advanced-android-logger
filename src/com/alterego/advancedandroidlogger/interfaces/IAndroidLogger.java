package com.alterego.advancedandroidlogger.interfaces;

import android.util.Log;

/**
 * This is the interface for our Android loggers. It has logging methods in two flavours, with tag and without.
 * The expected behaviour is that the tag may be passed in the constructor, but can also be overridden.
 * There is also the {@link setLoggingLevel} method that sets the logging level, below which log calls will not
 * be logged (e.g. if you set logging level to NORMAL, all the ILogger calls to verbose and debug methods will
 * not result in printed logs). Finally, there's the {@link getLogger} that will come in handy when setting log
 * levels and loggers for classes/methods.
 */
public interface IAndroidLogger {
	
	/**
	 * There are four logging levels = VERBOSE (Log.v), DEBUG (Log.d), NORMAL (Log.i) and WARNING (Log.w, Log.e and Log.wtf).
	 * Default Android and AdvancedAndroidLogger level is NORMAL. For details see  {@link Log}
	 */
	public static enum LoggingLevel {
        VERBOSE, DEBUG, NORMAL, WARNING
    }
	
	void verbose(String tag, String content);
	
	void verbose(String content);
	
	void debug(String tag, String content);
	
	void debug(String content);

	void info(String tag, String content);
	
	void info(String content);
	
	void warning(String tag, String content);
	
	void warning(String content);

    void error(String tag, String content);
    
    void error(String content);

    void fail(String tag, String content);
    
    void fail(String content);
    
    /**
     * Sets the logging level for the logger (there are 4 levels, default is NORMAL, see
     * {@link Log}.
     *
     * @param level Logging level = {
     * 				VERBOSE (Log.v, usually only for debugging releases), 
     * 				DEBUG (Log.d, preferably only for debugging releases), 
     * 				NORMAL (Log.i, Log.w, Log.e and Log.wtf - default and most common for public releases), 
     *              WARNING (Log.w, Log.e and Log.wtf for logging only the warnings and errors - only for public releases)
     *              }
     */
    void setLoggingLevel(LoggingLevel level);
    
    void setLoggingLevel(int level);
    
    int getLoggingLevel();
    
    IAndroidLogger getLogger(Object instance);
    
    String getLoggingTag(Object instance);

}
