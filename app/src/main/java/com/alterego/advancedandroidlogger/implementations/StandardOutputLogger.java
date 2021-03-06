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

import android.util.Log;

/**
 * This is the wrapper around the basic Android {@link Log} logger. It has logging methods in two flavours, with tag and without
 * (default tag is "ADVANCEDANDROIDLOGGER") - the tag may also be set via constructor, but can also be overridden.
 * {@link #setLoggingLevel(int)} method sets the logging level, below which log calls will not
 * be logged (e.g. if you set logging level to NORMAL, all the ILogger calls to verbose and debug methods will
 * not result in printed logs); default logging level is NORMAL i.e. INFO.
 */
public class StandardOutputLogger implements IAndroidLogger {

    private boolean mUseErrorOutput = false;

    private int mLoggingLevel = 2;

    private String mLoggingTag = "ADVANCEDANDROIDLOGGER";

    /**
     * Initializes the AndroidLogger with the default tag "LOGGER" and the
     * default debugging level NORMAL (Log.i).
     */
    public StandardOutputLogger() {
        this(null, LoggingLevel.NORMAL);
    }

    /**
     * Initializes the AndroidLogger with a specified tag and the
     * default debugging level NORMAL (Log.i).
     *
     * @param tag Logging tag
     */

    public StandardOutputLogger(String tag) {
        this(tag, LoggingLevel.NORMAL);
    }

    /**
     * Initializes the AndroidLogger with a specified tag and a specified logging
     * level.
     *
     * @param tag   Logging tag
     * @param level Logging level {@link LoggingLevel}
     */

    public StandardOutputLogger(String tag, LoggingLevel level) {
        if (tag != null) {
            mLoggingTag = tag;
        }
        setLoggingLevel(level);
    }

    /**
     * Initializes the AndroidLogger with a specified tag and a specified logging
     * level.
     *
     * @param tag   Logging tag
     * @param level Logging level {@link LoggingLevel}
     * @param useErrorOutput should print errors to Error output or to the standard console output
     */

    public StandardOutputLogger(String tag, LoggingLevel level, boolean useErrorOutput) {
        if (tag != null) {
            mLoggingTag = tag;
        }
        setLoggingLevel(level);
        mUseErrorOutput = useErrorOutput;
    }

    private void systemOut(String tag, String content) {
        String output = "";
        if (tag != null) {
            output += tag.toUpperCase();
        }
        if (content != null) {
            output += " " + content;
        }

        if (!output.equals("")) {
            System.out.println(output);
        }
    }

    private void errorOut(String tag, String content) {
        String output = "";
        if (tag != null) {
            output += tag.toUpperCase();
        }
        if (content != null) {
            output += " " + content;
        }

        if (!output.equals("")) {
            System.err.println(output);
        }
    }

    @Override
    public void verbose(String tag, String content) {
        if (mLoggingLevel == 0) {
            systemOut(tag, content);
        }
    }

    @Override
    public void verbose(String content) {
        verbose(mLoggingTag, content);
    }

    @Override
    public void debug(String tag, String content) {
        if (mLoggingLevel <= 1) {
            systemOut(tag, content);
        }
    }

    @Override
    public void debug(String content) {
        debug(mLoggingTag, content);
    }

    @Override
    public void d(Object message) {
        debug(String.valueOf(message));
    }

    @Override
    public void info(String tag, String content) {
        if (mLoggingLevel <= 2) {
            systemOut(tag, content);
        }
    }

    @Override
    public void info(String content) {
        info(mLoggingTag, content);
    }

    @Override
    public void warning(String tag, String content) {
        if (mUseErrorOutput) {
            errorOut(tag, content);
        } else {
            systemOut(tag, content);
        }
    }

    @Override
    public void warning(String content) {
        warning(mLoggingTag, content);
    }

    @Override
    public void error(String tag, String content) {
        if (mUseErrorOutput) {
            errorOut(tag, content);
        } else {
            systemOut(tag, content);
        }
    }

    @Override
    public void error(String content) {
        error(mLoggingTag, content);
    }

    @Override
    public void e(Object e) {
        if (e != null && e instanceof String) {
            error(mLoggingTag, (String) e);
        }

        if (e != null && ((e instanceof Throwable))) {
            if (((Throwable) e).getMessage().length() > 0) {
                error(mLoggingTag, ((Throwable) e).getMessage());
            } else {
                error(mLoggingTag, e.toString());
            }
        }
    }

    @Override
    public void e(String message, Throwable throwable) {
        error(mLoggingTag, message + " => " + throwable.getMessage());
    }

    @Override
    public void fail(String tag, String content) {
        if (content != null) {
            if (mUseErrorOutput) {
                errorOut(tag, content);
            } else {
                systemOut(tag, content);
            }
        }
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

        if (level == LoggingLevel.VERBOSE) {
            mLoggingLevel = 0;
        } else if (level == LoggingLevel.DEBUG) {
            mLoggingLevel = 1;
        } else if (level == LoggingLevel.NORMAL) {
            mLoggingLevel = 2;
        } else {
            mLoggingLevel = 3;
        }

    }

    @Override
    public void setLoggingLevel(int level) {
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
