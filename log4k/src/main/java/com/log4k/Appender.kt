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

/**
 * The [Log4k] singleton hold a set of these appenders to handle the logs.
 */
open class Appender(

    /**
     * A lambda function to handle the log. The first param is the log level, the second param is the full name of the
     * class that this log came from, and the third param is the event that we want to log.
     */
    val logger: (Level, String, event: Event) -> Unit
)
