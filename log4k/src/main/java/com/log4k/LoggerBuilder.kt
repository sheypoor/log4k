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

import com.log4k.Level.Assert

/**
 * A logger to handle the chain of assumptions. You have to notice that a failed assumption is logging on [Assert] level.
 */
class LoggerBuilder private constructor(
    private val clazz: String
) {
    companion object {

        /**
         * The creator method of this class.
         */
        fun create(clazz: String) = LoggerBuilder(clazz)
    }

    /**
     * Log an assertion with a [message].
     */
    private fun fail(message: String) =
        Log4k.log(Level.Assert, clazz, SimpleThrowableEvent(message, AssertionError(message)))

    /**
     * Log an assertion with a [message] if [condition] is false, else try the next assumption or run the [callback].
     */
    fun assumeTrue(
        message: String,
        condition: Boolean,
        callback: (() -> Unit)? = null
    ): LoggerBuilder? =
        if (condition) {
            callback?.let { it() }
            this
        } else {
            fail(message)
            null
        }

    /**
     * Log an assertion with a [message] if [condition] is true, else try the next assumption or run the [callback].
     */
    fun assumeFalse(message: String, condition: Boolean, callback: (() -> Unit)? = null) =
        assumeTrue(message, !condition, callback)

    /**
     * Log an assertion with a [message] if [condition] is not empty, else try the next assumption or run the [callback].
     */
    fun assumeEmpty(message: String, condition: String?, callback: (() -> Unit)? = null) =
        assumeTrue(message, condition?.isEmpty() ?: true, callback)

    /**
     * Log an assertion with a [message] if [collection] is not empty, else try the next assumption or run the [callback].
     */
    fun <C> assumeEmpty(
        message: String,
        collection: Collection<C>?,
        callback: (() -> Unit)? = null
    ) = assumeTrue(message, collection?.isEmpty() ?: true, callback)

    /**
     * Log an assertion with a [message] if [condition] is empty, else try the next assumption or run the [callback].
     */
    fun assumeNotEmpty(
        message: String,
        condition: String?,
        callback: (() -> Unit)? = null
    ) = assumeTrue(message, condition?.isNotEmpty() ?: false, callback)

    /**
     * Log an assertion with a [message] if [collection] is empty, else try the next assumption or run the [callback].
     */
    fun <C> assumeNotEmpty(
        message: String,
        collection: Collection<C>?,
        callback: (() -> Unit)? = null
    ) = assumeTrue(message, collection?.isNotEmpty() ?: false, callback)

    /**
     * Log an assertion with a [message] if [expected] value doesn't equal to [actual] value, else try the next assumption or run the [callback].
     */
    fun assumeEquals(
        message: String,
        expected: Any?,
        actual: Any?,
        callback: (() -> Unit)? = null
    ) = assumeTrue(message, expected == actual, callback)

    /**
     * Log an assertion with a [message] if [expected] value equals to [actual] value, else try the next assumption or run the [callback].
     */
    fun assumeNotEquals(
        message: String,
        expected: Any?,
        actual: Any?,
        callback: (() -> Unit)? = null
    ) = assumeTrue(message, expected != actual, callback)

    /**
     * Log an assertion with a [message] if [obj] is null, else try the next assumption or run the [callback].
     */
    fun assumeNotNull(message: String, obj: Any?, callback: (() -> Unit)? = null) =
        assumeTrue(message, obj != null, callback)

    /**
     * Log an assertion with a [message] if [obj] is not null, else try the next assumption or run the [callback].
     */
    fun assumeNull(message: String, obj: Any?, callback: (() -> Unit)? = null) =
        assumeTrue(message, obj == null, callback)

    /**
     * Log an assertion with a [message] if [expected] is not the same as [actual], else try the next assumption or run the [callback].
     */
    fun assumeSame(
        message: String,
        expected: Any?,
        actual: Any?,
        callback: (() -> Unit)? = null
    ) = assumeTrue(message, expected === actual, callback)

    /**
     * Log an assertion with a [message] if [expected] is the same as [actual], else try the next assumption or run the [callback].
     */
    fun assumeNotSame(
        message: String,
        expected: Any?,
        actual: Any?,
        callback: (() -> Unit)? = null
    ) = assumeTrue(message, expected !== actual, callback)
}
