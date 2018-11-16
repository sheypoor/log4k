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
package com.log4k.android

import android.util.Log
import com.log4k.*

/**
 * An appender to map log4k levels to Android log levels.
 */
class AndroidAppender(

    /**
     * A lambda function to resolve the message of the log.
     */
    private val generateMessage: (Event) -> String? = { event ->
        when (event) {
            is SimpleEvent -> {
                event.message
            }
            is SimpleThrowableEvent -> {
                event.message
            }
            else -> null
        }
    },

    /**
     * A lambda function to resolve the throwable of the log.
     */
    private val generateThrowable: (Event) -> Throwable? = { event ->
        when (event) {
            is SimpleThrowableEvent -> {
                event.throwable
            }
            else -> null
        }
    },

    /**
     * A lambda function to generate a string to show class name of the log
     */
    private val generateClassName: (String) -> String = {
        it.substring(it.lastIndexOf('.') + 1)
    }
) : Appender({ level, clazz, event ->
    val tag = generateClassName(clazz)
    generateMessage(event)?.let { message ->
        val throwable = generateThrowable(event)
        when (level.level) {
            Level.Verbose.level -> throwable?.let { Log.v(tag, message, it) } ?: run { Log.v(tag, message) }
            Level.Debug.level -> throwable?.let { Log.d(tag, message, it) } ?: run { Log.d(tag, message) }
            Level.Info.level -> throwable?.let { Log.i(tag, message, it) } ?: run { Log.i(tag, message) }
            Level.Warn.level -> throwable?.let { Log.w(tag, message, it) } ?: run { Log.w(tag, message) }
            Level.Error.level -> throwable?.let { Log.e(tag, message, it) } ?: run { Log.e(tag, message) }
            Level.Assert.level -> throwable?.let { Log.wtf(tag, message, it) } ?: run { Log.wtf(tag, message) }
            else -> {
            }
        }
    }
})