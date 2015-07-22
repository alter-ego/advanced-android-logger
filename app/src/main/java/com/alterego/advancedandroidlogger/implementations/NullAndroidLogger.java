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

/**
 * NullAndroidLogger should be used when declaring ILogger in the app (to avoid NullPointerExceptions when
 * calling uninstantiated ILogger (i.e. calling ILogger before we have a contest for instantiating)
 */
public class NullAndroidLogger<T> implements IAndroidLogger<T> {

    public static final IAndroidLogger instance = new NullAndroidLogger();

    private NullAndroidLogger() {
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
    public void d(T message) {
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
    public IAndroidLogger getLogger(Object instance) {
        return this;
    }

    @Override
    public void setLoggingLevel(int level) {
    }

    @Override
    public int getLoggingLevel() {
        return 0;
    }

    @Override
    public String getLoggingTag(Object instance) {
        return "";
    }

}
