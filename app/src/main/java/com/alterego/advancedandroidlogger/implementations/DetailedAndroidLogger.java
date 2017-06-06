package com.alterego.advancedandroidlogger.implementations;

/**
 * Copyright 2014 Alter Ego SRLS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.alterego.advancedandroidlogger.interfaces.IAndroidLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is the wrapper around {@link AndroidLogger} Android logger that adds additional class, method and line info
 * to the logging text. It is in the following format:
 * className.methodName() @ line lineNr ": " + normal logger content;
 */

public class DetailedAndroidLogger implements IAndroidLogger {

    private IAndroidLogger mLogger;

    private int mStackTraceLevel;

    private static final ArrayList<String> COMMON_STACKTRACE_CLASSNAMES = new ArrayList<String>
            (Arrays.asList("dalvik.system.VMStack",
                    "java.lang.Thread",
                    "com.alterego.advancedandroidlogger.implementations.DetailedAndroidLogger",
                    "com.alterego.advancedandroidlogger.implementations.MultiLoggerWrapper"));

    /**
     * Initializes the DetailedAndroidLogger with the default tag "LOGGER" and the
     * default debugging level NORMAL (Log.i).
     *
     * @deprecated use {@link DetailedAndroidLoggerBuilder}
     */
    @Deprecated
    public DetailedAndroidLogger() {
        this(null, LoggingLevel.NORMAL);
    }

    /**
     * Initializes the DetailedAndroidLogger with a specified tag and the
     * default debugging level NORMAL (Log.i).
     *
     * @param tag Logging tag
     * @deprecated use {@link DetailedAndroidLoggerBuilder}
     */
    @Deprecated
    public DetailedAndroidLogger(String tag) {
        this(tag, LoggingLevel.NORMAL);
    }

    /**
     * Initializes the DetailedAndroidLogger with a specified tag and a specified logging
     * level.
     *
     * @param tag   Logging tag
     * @param level Logging level {@link IAndroidLogger}
     * @deprecated use {@link DetailedAndroidLoggerBuilder}
     */

    @Deprecated
    public DetailedAndroidLogger(String tag, LoggingLevel level) {
        mLogger = new AndroidLogger(tag, level);
        mStackTraceLevel = findStackTraceLevel();
    }

    /**
     * Initializes the DetailedAndroidLogger with a specified tag and a specified logging
     * level.
     *
     * @param tag               Logging tag
     * @param level             Logging level {@link IAndroidLogger}
     * @param useStandardOutput Should the logger use standard System output or android Log output
     * @deprecated use {@link DetailedAndroidLoggerBuilder}
     */

    @Deprecated
    public DetailedAndroidLogger(String tag, LoggingLevel level, boolean useStandardOutput) {
        if (useStandardOutput) {
            mLogger = new StandardOutputLogger(tag, level);
        } else {
            mLogger = new AndroidLogger(tag, level);
        }
        mStackTraceLevel = findStackTraceLevel();
    }

    /**
     * Initializes the DetailedAndroidLogger with a specified tag, logging level, stack trace level and standard output switch.
     *
     * @param tag               Logging tag
     * @param level             Logging level {@link IAndroidLogger}
     * @param useStandardOutput Should the logger use standard System output or android Log output
     * @param stackTraceLevel   Stack trace level for printing (-1 for auto-finding, might not always work)
     */

    public DetailedAndroidLogger(String tag, LoggingLevel level, boolean useStandardOutput, int stackTraceLevel) {
        if (useStandardOutput) {
            mLogger = new StandardOutputLogger(tag, level);
        } else {
            mLogger = new AndroidLogger(tag, level);
        }

        if (stackTraceLevel == -1) {
            mStackTraceLevel = findStackTraceLevel();
        } else {
            mStackTraceLevel = stackTraceLevel;
        }
    }

    private int findStackTraceLevel() {
        int level_index;
        String fullClassName;

        for (level_index = 0; level_index < Thread.currentThread().getStackTrace().length; level_index++) {
            fullClassName = Thread.currentThread().getStackTrace()[level_index].getClassName();
            if (!COMMON_STACKTRACE_CLASSNAMES.contains(fullClassName)) {
                break;
            }
        }
        return level_index;
    }


    private String getDetails(String content) {
        String details = "";

        try {
            if (mStackTraceLevel < Thread.currentThread().getStackTrace().length) {
                if (COMMON_STACKTRACE_CLASSNAMES.contains(Thread.currentThread().getStackTrace()[mStackTraceLevel].getClassName())) {
                    mStackTraceLevel = findStackTraceLevel();
                }

                String fullClassName = Thread.currentThread().getStackTrace()[mStackTraceLevel].getClassName();
                String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
                String methodName = Thread.currentThread().getStackTrace()[mStackTraceLevel].getMethodName();
                int lineNumber = Thread.currentThread().getStackTrace()[mStackTraceLevel].getLineNumber();
                details = className + "." + methodName + "() @ line " + lineNumber + ": " + content;
            }
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
    public void d(Object message) {
        if (message instanceof List) {
            for (int i = 0; i < ((List) message).size() || i < 10; i++) {
                mLogger.debug(String.valueOf(message));
            }
        } else {
            mLogger.debug(getDetails(String.valueOf(message)));
        }
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

    /**
     * Print an error message. The red ones on logcat
     *
     * @param e {@link String} or {@link Throwable}
     *
     *          If {@link Throwable} has a message, it will be printed. Otherwise, the whole object will be printed with {@code toString()}.
     */

    @Override
    public void e(Object e) {
        if (e != null && e instanceof String) {
            error((String) e);
        }

        if (e != null && ((e instanceof Throwable))) {
            if (((Throwable) e).getMessage().length() > 0) {
                error(((Throwable) e).getMessage());
            } else {
                error(e.toString());
            }
        }
    }

    @Override
    public void e(String message, Throwable throwable) {

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

    public static class DetailedAndroidLoggerBuilder {

        private String tag = null;

        private LoggingLevel level = LoggingLevel.NORMAL;

        private int stackTraceLevel = 0;

        private boolean useStandard = false;

        public static DetailedAndroidLoggerBuilder get() {
            return new DetailedAndroidLoggerBuilder();
        }

        public DetailedAndroidLoggerBuilder withTag(String tag) {
            this.tag = tag;
            return this;
        }

        public DetailedAndroidLoggerBuilder withLoggingLevel(LoggingLevel level) {
            this.level = level;
            return this;
        }

        public DetailedAndroidLoggerBuilder withStacktraceLevel(int level) {
            this.stackTraceLevel = level;
            return this;
        }

        public DetailedAndroidLoggerBuilder withUsingStandardOutput(boolean useStandard) {
            this.useStandard = useStandard;
            return this;
        }

        public DetailedAndroidLogger build() {
            return new DetailedAndroidLogger(tag, level, useStandard, stackTraceLevel);
        }

    }

}
