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
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.log4k

import com.log4k.Level.*

/**
 * Send a [Verbose] log [message].
 */
inline fun <reified T> T.v(message: String) = Log4k.log(Level.Verbose, T::class.java.name, SimpleEvent(message))

/**
 * Send a [Debug] log [message].
 */
inline fun <reified T> T.d(message: String) = Log4k.log(Level.Debug, T::class.java.name, SimpleEvent(message))

/**
 * Send an [Info] log [message].
 */
inline fun <reified T> T.i(message: String) = Log4k.log(Level.Info, T::class.java.name, SimpleEvent(message))

/**
 * Send a [Warn] log [message].
 */
inline fun <reified T> T.w(message: String) = Log4k.log(Level.Warn, T::class.java.name, SimpleEvent(message))

/**
 * Send an [Error] log [message].
 */
inline fun <reified T> T.e(message: String) = Log4k.log(Level.Error, T::class.java.name, SimpleEvent(message))

/**
 * Send an [Assert] log [message].
 */
inline fun <reified T> T.a(message: String) = Log4k.log(Level.Assert, T::class.java.name, SimpleEvent(message))

/**
 * Send a [Verbose] log [message] and [throwable].
 */
inline fun <reified T> T.v(message: String, throwable: Throwable) =
    Log4k.log(Level.Verbose, T::class.java.name, SimpleThrowableEvent(message, throwable))

/**
 * Send a [Debug] log [message] and [throwable].
 */
inline fun <reified T> T.d(message: String, throwable: Throwable) =
    Log4k.log(Level.Debug, T::class.java.name, SimpleThrowableEvent(message, throwable))

/**
 * Send an [Info] log [message] and [throwable].
 */
inline fun <reified T> T.i(message: String, throwable: Throwable) =
    Log4k.log(Level.Info, T::class.java.name, SimpleThrowableEvent(message, throwable))

/**
 * Send a [Warn] log [message] and [throwable].
 */
inline fun <reified T> T.w(message: String, throwable: Throwable) =
    Log4k.log(Level.Warn, T::class.java.name, SimpleThrowableEvent(message, throwable))

/**
 * Send an [Error] log [message] and [throwable].
 */
inline fun <reified T> T.e(message: String, throwable: Throwable) =
    Log4k.log(Level.Error, T::class.java.name, SimpleThrowableEvent(message, throwable))

/**
 * Send an [Assert] log [message] and [throwable].
 */
inline fun <reified T> T.a(message: String, throwable: Throwable) =
    Log4k.log(Level.Assert, T::class.java.name, SimpleThrowableEvent(message, throwable))

/**
 * Send a [Verbose] log [event].
 */
inline fun <reified T> T.v(event: Event) =
    Log4k.log(Level.Verbose, T::class.java.name, event)

/**
 * Send a [Debug] log [event].
 */
inline fun <reified T> T.d(event: Event) =
    Log4k.log(Level.Debug, T::class.java.name, event)

/**
 * Send an [Info] log [event].
 */
inline fun <reified T> T.i(event: Event) =
    Log4k.log(Level.Info, T::class.java.name, event)

/**
 * Send a [Warn] log [event].
 */
inline fun <reified T> T.w(event: Event) =
    Log4k.log(Level.Warn, T::class.java.name, event)

/**
 * Send an [Error] log [event].
 */
inline fun <reified T> T.e(event: Event) =
    Log4k.log(Level.Error, T::class.java.name, event)

/**
 * Send an [Assert] log [event].
 */
inline fun <reified T> T.a(event: Event) =
    Log4k.log(Level.Assert, T::class.java.name, event)

/**
 * Log an assertion with a [message].
 */
inline fun <reified T> T.fail(message: String) = fail(message, AssertionError(message))

/**
 * Log an assertion with a [message] and [throwable].
 */
inline fun <reified T> T.fail(message: String, throwable: Throwable) = a(message, throwable)

/**
 * Log an assertion with a [message] if [condition] is false, else try the next assumption or run the [callback].
 */
inline fun <reified T> T.assumeTrue(
    message: String,
    condition: Boolean,
    noinline callback: (() -> Unit)? = null
): LoggerBuilder? = LoggerBuilder.create(T::class.java.name).assumeTrue(message, condition, callback)

/**
 * Log an assertion with a [message] if [condition] is true, else try the next assumption or run the [callback].
 */
inline fun <reified T> T.assumeFalse(message: String, condition: Boolean, noinline callback: (() -> Unit)? = null) =
    LoggerBuilder.create(T::class.java.name).assumeFalse(message, condition, callback)

/**
 * Log an assertion with a [message] if [condition] is not empty, else try the next assumption or run the [callback].
 */
inline fun <reified T> T.assumeEmpty(message: String, condition: String?, noinline callback: (() -> Unit)? = null) =
    LoggerBuilder.create(T::class.java.name).assumeEmpty(message, condition, callback)

/**
 * Log an assertion with a [message] if [collection] is not empty, else try the next assumption or run the [callback].
 */
inline fun <reified T, C> T.assumeEmpty(
    message: String,
    collection: Collection<C>?,
    noinline callback: (() -> Unit)? = null
) = LoggerBuilder.create(T::class.java.name).assumeEmpty(message, collection, callback)

/**
 * Log an assertion with a [message] if [condition] is empty, else try the next assumption or run the [callback].
 */
inline fun <reified T> T.assumeNotEmpty(message: String, condition: String?, noinline callback: (() -> Unit)? = null) =
    LoggerBuilder.create(T::class.java.name).assumeNotEmpty(message, condition, callback)

/**
 * Log an assertion with a [message] if [collection] is empty, else try the next assumption or run the [callback].
 */
inline fun <reified T, C> T.assumeNotEmpty(
    message: String,
    collection: Collection<C>?,
    noinline callback: (() -> Unit)? = null
) = LoggerBuilder.create(T::class.java.name).assumeNotEmpty(message, collection, callback)

/**
 * Log an assertion with a [message] if [expected] value doesn't equal to [actual] value, else try the next assumption or run the [callback].
 */
inline fun <reified T> T.assumeEquals(
    message: String,
    expected: Any?,
    actual: Any?,
    noinline callback: (() -> Unit)? = null
) = LoggerBuilder.create(T::class.java.name).assumeEquals(message, expected, actual, callback)

/**
 * Log an assertion with a [message] if [expected] value equals to [actual] value, else try the next assumption or run the [callback].
 */
inline fun <reified T> T.assumeNotEquals(
    message: String,
    expected: Any?,
    actual: Any?,
    noinline callback: (() -> Unit)? = null
) = LoggerBuilder.create(T::class.java.name).assumeNotEquals(message, expected, actual, callback)

/**
 * Log an assertion with a [message] if [obj] is null, else try the next assumption or run the [callback].
 */
inline fun <reified T> T.assumeNotNull(message: String, obj: Any?, noinline callback: (() -> Unit)? = null) =
    LoggerBuilder.create(T::class.java.name).assumeNotNull(message, obj, callback)

/**
 * Log an assertion with a [message] if [obj] is not null, else try the next assumption or run the [callback].
 */
inline fun <reified T> T.assumeNull(message: String, obj: Any?, noinline callback: (() -> Unit)? = null) =
    LoggerBuilder.create(T::class.java.name).assumeNull(message, obj, callback)

/**
 * Log an assertion with a [message] if [expected] is not the same as [actual], else try the next assumption or run the [callback].
 */
inline fun <reified T> T.assumeSame(
    message: String,
    expected: Any?,
    actual: Any?,
    noinline callback: (() -> Unit)? = null
) = LoggerBuilder.create(T::class.java.name).assumeSame(message, expected, actual, callback)

/**
 * Log an assertion with a [message] if [expected] is the same as [actual], else try the next assumption or run the [callback].
 */
inline fun <reified T> T.assumeNotSame(
    message: String,
    expected: Any?,
    actual: Any?,
    noinline callback: (() -> Unit)? = null
) = LoggerBuilder.create(T::class.java.name).assumeNotSame(message, expected, actual, callback)