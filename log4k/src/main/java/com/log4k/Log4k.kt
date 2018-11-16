/**
 * Copyright 2018 Hadi Lashkari Ghouchani

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.log4k

import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.regex.Pattern

object Log4k {

    private var appenders = Collections.newSetFromMap(ConcurrentHashMap<AppenderWrapper, Boolean>())

    /**
     * Adds an appender to log.
     *
     * Match Inheritance Rule:
     *   An appender with a defined [pattern] is enabled if its [pattern] matches the full class name of the log
     *   request.
     *
     * Basic Selection Rule:
     *   A log request of level p in a logger with [level] q, is enabled if p >= q.
     *
     * @param level in the basic selection rule above, this is the q param.
     * @param pattern is a Regex to match the full class names which we want to log.
     * @param appender is responsible for handling logs.
     */
    fun add(level: Level, pattern: Pattern, appender: Appender): Log4k {
        Log4k.appenders.add(AppenderWrapper(level, pattern, appender))
        return this
    }

    /**
     * The same as above [add] method.

     * @param pattern matches to the full class names which we want to log.
     */
    fun add(level: Level, pattern: String, appender: Appender): Log4k {
        return add(level, Pattern.compile(pattern), appender)
    }

    /**
     * Removes the [appender] to avoid logging anymore.
     */
    fun remove(appender: Appender) {
        val iterator = appenders.iterator()
        while (iterator.hasNext()) {
            val w = iterator.next()
            if (w.appender === appender) {
                iterator.remove()
                break
            }
        }
    }

    /**
     * Log [event]s based on "Basic Selection Rule" and "Match Inheritance Rule" below.
     *
     * Match Inheritance Rule:
     *   An appender with a defined pattern is enabled if its pattern matches the [clazz] (Full class name) of the log
     *   request.
     *
     * Basic Selection Rule:
     *   A log request of level [p] in a logger with level q, is enabled if [p] >= q.
     */
    fun log(p: Level, clazz: String, event: Event) {
        appenders.forEach {
            val q = it.level
            val matcher = it.pattern.matcher(clazz)
            if (p.level >= q.level && matcher.matches()) {
                it.appender.logger(p, clazz, event)
            }
        }
    }

    private class AppenderWrapper(
        val level: Level,
        val pattern: Pattern,
        val appender: Appender
    )
}