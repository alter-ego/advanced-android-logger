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

public class MultiLoggerWrapper implements IAndroidLogger {

    private ArrayList<IAndroidLogger> mLoggers;

    public MultiLoggerWrapper() {
        mLoggers = new ArrayList<IAndroidLogger>();
    }

    public void registerLogger(IAndroidLogger logger) {
        mLoggers.add(logger);
    }

    public void unregisterLogger(IAndroidLogger logger) {
        mLoggers.remove(logger);
    }

    @Override
    public void verbose(String tag, String content) {
        for (IAndroidLogger logger : mLoggers) {
            logger.verbose(tag, content);
        }
    }

    @Override
    public void verbose(String tag) {
        for (IAndroidLogger logger : mLoggers) {
            logger.verbose(tag);
        }
    }

    @Override
    public void debug(String tag, String content) {
        for (IAndroidLogger logger : mLoggers) {
            logger.debug(tag, content);
        }
    }

    @Override
    public void debug(String tag) {
        for (IAndroidLogger logger : mLoggers) {
            logger.debug(tag);
        }
    }

    @Override
    public void d(Object message) {
        debug(String.valueOf(message));
    }

    @Override
    public void info(String tag, String content) {
        for (IAndroidLogger logger : mLoggers) {
            logger.info(tag, content);
        }
    }

    @Override
    public void info(String tag) {
        for (IAndroidLogger logger : mLoggers) {
            logger.info(tag);
        }
    }

    @Override
    public void warning(String tag, String content) {
        for (IAndroidLogger logger : mLoggers) {
            logger.warning(tag, content);
        }
    }

    @Override
    public void warning(String tag) {
        for (IAndroidLogger logger : mLoggers) {
            logger.warning(tag);
        }
    }

    @Override
    public void error(String tag, String content) {
        for (IAndroidLogger logger : mLoggers) {
            logger.error(tag, content);
        }
    }

    @Override
    public void error(String tag) {
        for (IAndroidLogger logger : mLoggers) {
            logger.error(tag);
        }
    }

    @Override
    public void fail(String tag, String content) {
        for (IAndroidLogger logger : mLoggers) {
            logger.fail(tag, content);
        }
    }

    @Override
    public void fail(String tag) {
        for (IAndroidLogger logger : mLoggers) {
            logger.fail(tag);
        }
    }

    @Override
    public void setLoggingLevel(LoggingLevel loggingLevel) {
        for (IAndroidLogger logger : mLoggers) {
            logger.setLoggingLevel(loggingLevel);
        }
    }

    @Override
    public void setLoggingLevel(int level) {
        for (IAndroidLogger logger : mLoggers) {
            logger.setLoggingLevel(level);
        }
    }

    @Override
    public int getLoggingLevel() {
        if (mLoggers.size() > 0) {
            return mLoggers.get(0).getLoggingLevel();
        } else {
            return 2;
        }
    }

    @Override
    public IAndroidLogger getLogger(Object o) {
        return this;
    }

    @Override
    public String getLoggingTag(Object o) {
        if (mLoggers.size() > 0) {
            return mLoggers.get(0).getLoggingTag(o);
        }
        return "";
    }
}
