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

import com.log4k.util.TestAppender
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.StringWriter

@RunWith(JUnit4::class)
class LoggerTest {

    private lateinit var out: StringWriter
    private lateinit var appender: TestAppender

    @Before
    fun setup() {
        out = StringWriter()
        appender = TestAppender(out)
        Log4k.add(Level.Verbose, ".*", appender)
    }

    @After
    fun remove() {
        Log4k.remove(appender)
        appender.close()
    }

    @Test
    fun testVerbose() {
        v("test")
        assertEquals("V/LoggerTest: test\n", out.toString())
    }

    @Test
    fun testDebug() {
        d("test")
        assertEquals("D/LoggerTest: test\n", out.toString())
    }

    @Test
    fun testInfo() {
        i("test")
        assertEquals("I/LoggerTest: test\n", out.toString())
    }

    @Test
    fun testWarn() {
        w("test")
        assertEquals("W/LoggerTest: test\n", out.toString())
    }

    @Test
    fun testError() {
        e("test")
        assertEquals("E/LoggerTest: test\n", out.toString())
    }

    @Test
    fun testAssert() {
        a("test")
        assertEquals("A/LoggerTest: test\n", out.toString())
    }

    @Test
    fun testVerboseThrowable() {
        v("test", RuntimeException("testing"))
        assertTrue(out.toString().startsWith("V/LoggerTest: test\njava.lang.RuntimeException: testing"))
    }

    @Test
    fun testDebugThrowable() {
        d("test", RuntimeException("testing"))
        assertTrue(out.toString().startsWith("D/LoggerTest: test\njava.lang.RuntimeException: testing"))
    }

    @Test
    fun testInfoThrowable() {
        i("test", RuntimeException("testing"))
        assertTrue(out.toString().startsWith("I/LoggerTest: test\njava.lang.RuntimeException: testing"))
    }

    @Test
    fun testWarnThrowable() {
        w("test", RuntimeException("testing"))
        assertTrue(out.toString().startsWith("W/LoggerTest: test\njava.lang.RuntimeException: testing"))
    }

    @Test
    fun testErrorThrowable() {
        e("test", RuntimeException("testing"))
        assertTrue(out.toString().startsWith("E/LoggerTest: test\njava.lang.RuntimeException: testing"))
    }

    @Test
    fun testAssertThrowable() {
        a("test", RuntimeException("testing"))
        assertTrue(out.toString().startsWith("A/LoggerTest: test\njava.lang.RuntimeException: testing"))
    }

    @Test
    fun testFail() {
        fail("test")
        assertTrue(out.toString().startsWith("A/LoggerTest: test\ncom.log4k.AssertionError: test"))
    }

    @Test
    fun testFailThrowable() {
        fail("test", RuntimeException("testing"))
        assertTrue(out.toString().startsWith("A/LoggerTest: test\njava.lang.RuntimeException: testing"))
    }

    @Test
    fun testTrueAssumeTrue() {
        assumeTrue("test", true)
        assertEquals("", out.toString())
    }

    @Test
    fun testFalseAssumeTrue() {
        assumeTrue("test", false)
        assertTrue(out.toString().startsWith("A/LoggerTest: test\ncom.log4k.AssertionError: test"))
    }

    @Test
    fun testTrueAssumeFalse() {
        assumeFalse("test", false)
        assertEquals("", out.toString())
    }

    @Test
    fun testFalseAssumeFalse() {
        assumeFalse("test", true)
        assertTrue(out.toString().startsWith("A/LoggerTest: test\ncom.log4k.AssertionError: test"))
    }

    @Test
    fun testTrueAssumeEmpty() {
        assumeEmpty("test", "")
        assertEquals("", out.toString())
    }

    @Test
    fun testTrueAssumeEmptyNull() {
        assumeEmpty("test", null as String?)
        assertEquals("", out.toString())
    }

    @Test
    fun testFalseAssumeEmpty() {
        assumeEmpty("test", "sdfvv")
        assertTrue(out.toString().startsWith("A/LoggerTest: test\ncom.log4k.AssertionError: test"))
    }

    @Test
    fun testTrueAssumeEmptyCollection() {
        assumeEmpty("test", listOf<String>())
        assertEquals("", out.toString())
    }

    @Test
    fun testTrueAssumeEmptyNullCollection() {
        assumeEmpty("test", null as List<String>?)
        assertEquals("", out.toString())
    }

    @Test
    fun testFalseAssumeEmptyCollection() {
        assumeEmpty("test", listOf("skfjnv"))
        assertTrue(out.toString().startsWith("A/LoggerTest: test\ncom.log4k.AssertionError: test"))
    }

    @Test
    fun testTrueAssumeNotEmpty() {
        assumeNotEmpty("test", "slfkdjnv")
        assertEquals("", out.toString())
    }

    @Test
    fun testFalseAssumeNotEmptyNull() {
        assumeNotEmpty("test", null as String?)
        assertTrue(out.toString().startsWith("A/LoggerTest: test\ncom.log4k.AssertionError: test"))
    }

    @Test
    fun testFalseAssumeNotEmpty() {
        assumeNotEmpty("test", "")
        assertTrue(out.toString().startsWith("A/LoggerTest: test\ncom.log4k.AssertionError: test"))
    }

    @Test
    fun testTrueAssumeNotEmptyCollection() {
        assumeNotEmpty("test", listOf("slkdfjv"))
        assertEquals("", out.toString())
    }

    @Test
    fun testFalseAssumeNotEmptyNullCollection() {
        assumeNotEmpty("test", null as List<String>?)
        assertTrue(out.toString().startsWith("A/LoggerTest: test\ncom.log4k.AssertionError: test"))
    }

    @Test
    fun testFalseAssumeNotEmptyCollection() {
        assumeNotEmpty("test", listOf<String>())
        assertTrue(out.toString().startsWith("A/LoggerTest: test\ncom.log4k.AssertionError: test"))
    }

    @Test
    fun testTrueAssumeEquals() {
        assumeEquals("test", "a", "a")
        assertEquals("", out.toString())
    }

    @Test
    fun testFalseAssumeEquals() {
        assumeEquals("test", "a", "b")
        assertTrue(out.toString().startsWith("A/LoggerTest: test\ncom.log4k.AssertionError: test"))
    }

    @Test
    fun testTrueAssumeNotEquals() {
        assumeNotEquals("test", "a", "b")
        assertEquals("", out.toString())
    }

    @Test
    fun testFalseAssumeNotEquals() {
        assumeNotEquals("test", "a", "a")
        assertTrue(out.toString().startsWith("A/LoggerTest: test\ncom.log4k.AssertionError: test"))
    }

    @Test
    fun testTrueAssumeNotNull() {
        assumeNotNull("test", "")
        assertEquals("", out.toString())
    }

    @Test
    fun testFalseAssumeNotNull() {
        assumeNotNull("test", null)
        assertTrue(out.toString().startsWith("A/LoggerTest: test\ncom.log4k.AssertionError: test"))
    }

    @Test
    fun testTrueAssumeNull() {
        assumeNull("test", null)
        assertEquals("", out.toString())
    }

    @Test
    fun testFalseAssumeNull() {
        assumeNull("test", "")
        assertTrue(out.toString().startsWith("A/LoggerTest: test\ncom.log4k.AssertionError: test"))
    }

    @Test
    fun testTrueAssumeSame() {
        val a = Any()
        assumeSame("test", a, a)
        assertEquals("", out.toString())
    }

    @Test
    fun testFalseAssumeSame() {
        assumeSame("test", Any(), Any())
        assertTrue(out.toString().startsWith("A/LoggerTest: test\ncom.log4k.AssertionError: test"))
    }

    @Test
    fun testTrueAssumeNotSame() {
        assumeNotSame("test", Any(), Any())
        assertEquals("", out.toString())
    }

    @Test
    fun testFalseAssumeNotSame() {
        val a = Any()
        assumeNotSame("test", a, a)
        assertTrue(out.toString().startsWith("A/LoggerTest: test\ncom.log4k.AssertionError: test"))
    }

    @Test
    fun testMultiAssert() {
        val message = "Assumptions are correct"
        assumeTrue("test1", true)
            ?.assumeTrue("test2", true)
            ?.assumeFalse("test3", false) {
                out.write(message)
            }
        assertEquals(message, out.toString())
    }
}