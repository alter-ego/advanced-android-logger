package com.alterego.advancedandroidlogger.interfaces;

public interface ILogger {
	
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
    
    ILogger getLogger(Object instance);


}
