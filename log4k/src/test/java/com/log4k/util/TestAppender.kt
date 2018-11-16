package com.log4k.util

import com.log4k.DefaultAppender
import java.io.PrintWriter
import java.io.StringWriter

class TestAppender(
    private val out: StringWriter
) : DefaultAppender(writer = PrintWriter(out), generateTimestamp = { "" })