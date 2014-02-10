package com.alterego.advancedandroidlogger.implementations;

import com.alterego.advancedandroidlogger.interfaces.IAndroidLogger;

import java.util.ArrayList;

public class MultiLoggerWrapper implements IAndroidLogger {

    private ArrayList<IAndroidLogger> mLoggers;

    public MultiLoggerWrapper() {
        mLoggers = new ArrayList<IAndroidLogger> ();
    }

    public void registerLogger(IAndroidLogger logger) {
        mLoggers.add(logger);
    }

    public void unregisterLogger(IAndroidLogger logger) {
        mLoggers.remove(logger);
    }

    @Override
    public void verbose(String tag, String content) {
        for (IAndroidLogger logger: mLoggers)
            logger.verbose(tag, content);
    }

    @Override
    public void verbose(String tag) {
        for (IAndroidLogger logger: mLoggers)
            logger.verbose(tag);
    }

    @Override
    public void debug(String tag, String content) {
        for (IAndroidLogger logger: mLoggers)
            logger.debug(tag, content);
    }

    @Override
    public void debug(String tag) {
        for (IAndroidLogger logger: mLoggers)
            logger.debug(tag);
    }

    @Override
    public void info(String tag, String content) {
        for (IAndroidLogger logger: mLoggers)
            logger.info(tag, content);
    }

    @Override
    public void info(String tag) {
        for (IAndroidLogger logger: mLoggers)
            logger.info(tag);
    }

    @Override
    public void warning(String tag, String content) {
        for (IAndroidLogger logger: mLoggers)
            logger.warning(tag, content);
    }

    @Override
    public void warning(String tag) {
        for (IAndroidLogger logger: mLoggers)
            logger.warning(tag);
    }

    @Override
    public void error(String tag, String content) {
        for (IAndroidLogger logger: mLoggers)
            logger.error(tag, content);
    }

    @Override
    public void error(String tag) {
        for (IAndroidLogger logger: mLoggers)
            logger.error(tag);
    }

    @Override
    public void fail(String tag, String content) {
        for (IAndroidLogger logger: mLoggers)
            logger.fail(tag, content);
    }

    @Override
    public void fail(String tag) {
        for (IAndroidLogger logger: mLoggers)
            logger.fail(tag);
    }

    @Override
    public void setLoggingLevel(LoggingLevel loggingLevel) {
        for (IAndroidLogger logger: mLoggers)
            logger.setLoggingLevel(loggingLevel);
    }

    @Override
    public void setLoggingLevel(int level) {
        for (IAndroidLogger logger: mLoggers)
            logger.setLoggingLevel(level);
    }

    @Override
    public int getLoggingLevel() {
        if (mLoggers.size()>0)
            return mLoggers.get(0).getLoggingLevel();
        else return 2;
    }

    @Override
    public IAndroidLogger getLogger(Object o) {
        return this;
    }

    @Override
    public String getLoggingTag(Object o) {
        if (mLoggers.size()>0)
            return mLoggers.get(0).getLoggingTag(o);
        return "";
    }
}
