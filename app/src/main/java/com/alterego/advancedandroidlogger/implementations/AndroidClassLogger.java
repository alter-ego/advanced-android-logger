package com.alterego.advancedandroidlogger.implementations;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.alterego.advancedandroidlogger.helpers.Pattern;
import com.alterego.advancedandroidlogger.interfaces.IAndroidLogger;

public class AndroidClassLogger implements IAndroidLogger {
	private IAndroidLogger mLogger;
    private Map<String, LoggingLevel> presetLevels;
    private Map<Pattern, LoggingLevel> presetPatterns;
    private boolean mUseDetailedLogger = false;

	/**
	 * Initializes the AndroidClassLogger with the default tag "LOGGER" and the 
	 * default debugging level NORMAL (Log.i).
	 *
	 * @param tag Logging tag
	 */
	public AndroidClassLogger() {
		this(null, LoggingLevel.NORMAL, false);
	}

	/**
	 * Initializes the AndroidClassLogger with a specified tag and the 
	 * default debugging level NORMAL (Log.i).
	 *
	 * @param tag Logging tag
	 * @param use_detailed_logger Should we use {@link DetailedAndroidLogger} or normal {@link AndroidLogger}
	 */

	public AndroidClassLogger(String tag, boolean use_detailed_logger) {
		this(tag, LoggingLevel.NORMAL, use_detailed_logger);
		mUseDetailedLogger = use_detailed_logger;
	}

	/**
	 * Initializes the AndroidClassLogger with a specified tag and a specified logging
	 * level.
	 *
	 * @param tag   Logging tag
	 * @param level Logging level {@link IAndroidLogger}
	 * @param use_detailed_logger Should we use {@link DetailedAndroidLogger} or normal {@link AndroidLogger}
	 */

	public AndroidClassLogger(String tag, LoggingLevel level, boolean use_detailed_logger) {
		if (use_detailed_logger)
			mLogger = new DetailedAndroidLogger (tag, level);
		else
			mLogger = new AndroidLogger (tag, level);
		
		mUseDetailedLogger = use_detailed_logger;
	}

	public void setLoggingLevelFor(Class<? extends Object> cls, LoggingLevel level) {
		if (this.presetLevels == null) {
			this.presetLevels = new HashMap<String, LoggingLevel>();
		}

		this.presetLevels.put(cls.getSimpleName(), level);
	}

	public void setLoggingLevelFor(String subTag, LoggingLevel level) {
		if (this.presetLevels == null) {
			this.presetLevels = new HashMap<String, LoggingLevel>();
		}

		this.presetLevels.put(subTag, level);
	}

	public void setLoggingLevelFor(Pattern regex, LoggingLevel level) {
		if (this.presetPatterns == null) {
			this.presetPatterns = new HashMap<Pattern, LoggingLevel>();
		}

		this.presetPatterns.put(regex, level);
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
	public void d(Object message) {

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
	public void e(Object e) {

	}

	@Override
	public void e(String message, Throwable throwable) {
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
	public IAndroidLogger getLogger(Object instance) {
		String subTag = instance.getClass().getSimpleName();
		//AndroidClassLogger retval = new AndroidClassLogger(mLogger.getLoggingTag() + "/" + subTag, mUseDetailedLogger);
		AndroidClassLogger retval = new AndroidClassLogger(getLoggingTag(instance), mUseDetailedLogger);
        retval.setLoggingLevel(mLogger.getLoggingLevel());

        if (presetLevels != null && presetLevels.containsKey(subTag)) {
            retval.setLoggingLevel(presetLevels.get(subTag));
        } else if (presetPatterns != null) {
            for (Entry<Pattern, LoggingLevel> ent : presetPatterns.entrySet()) {
                if (ent.getKey().matcher(subTag).matches()) {
                    retval.setLoggingLevel(ent.getValue());
                    break;
                }
            }
        }

        if (presetLevels != null)
            retval.presetLevels = new HashMap<>(presetLevels);

        if (presetPatterns != null)
            retval.presetPatterns = new HashMap<>(presetPatterns);

        return retval;

	}

	@Override
	public String getLoggingTag(Object instance) {
		String subTag = instance.getClass().getSimpleName();
		return mLogger.getLoggingTag(instance) + "/" + subTag;
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
