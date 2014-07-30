package com.alterego.advancedandroidlogger.helpers;

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
 * 
 */

public class GroupInfo {
    private int position;
    private int index;

    public GroupInfo(int index, int position) {
        this.index = index;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public int getIndex() {
        return index;
    }
}
