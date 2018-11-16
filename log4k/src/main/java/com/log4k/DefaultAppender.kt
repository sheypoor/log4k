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

import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple and default appender.
 */
open class DefaultAppender(

    /**
     * Print writer to handle the output of this appender.
     */
    private val writer: PrintWriter = PrintWriter(System.out),

    /**
     * A lambda function to generate a string of the timestamp of the log.
     */
    private val generateTimestamp: () -> String = {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
        sdf.timeZone = Calendar.getInstance().timeZone
        "${sdf.format(Date())}/ "
    },

    /**
     * A lambda function to generate a string to show level of the log.
     */
    private val generateLevel: (Level) -> String = {
        when (it.level) {
            Level.Verbose.level -> "V/"
            Level.Debug.level -> "D/"
            Level.Info.level -> "I/"
            Level.Warn.level -> "W/"
            Level.Error.level -> "E/"
            Level.Assert.level -> "A/"
            else -> ""
        }
    },

    /**
     * A lambda function to generate a string to show class name of the log
     */
    private val generateClassName: (String) -> String = {
        "${it.substring(it.lastIndexOf('.') + 1)}: "
    }
) : Appender({ level, clazz, event ->
    when (event) {
        is SimpleEvent -> {
            writer.println("${generateTimestamp()}${generateLevel(level)}${generateClassName(clazz)}${event.message}")
        }
        is SimpleThrowableEvent -> {
            writer.println("${generateTimestamp()}${generateLevel(level)}${generateClassName(clazz)}${event.message}")
            event.throwable.printStackTrace(writer)
        }
    }
    writer.flush()
}) {
    open fun close() {
        writer.close()
    }
}