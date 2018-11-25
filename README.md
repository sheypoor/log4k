 [![Build Status](https://travis-ci.com/sheypoor/log4k.svg?branch=master)](https://travis-ci.com/sheypoor/log4k)
 [ ![Download](https://api.bintray.com/packages/hadilq/Sheypoor/log4k/images/download.svg) ](https://bintray.com/hadilq/Sheypoor/log4k/_latestVersion)
 [ ![Download](https://api.bintray.com/packages/hadilq/Sheypoor/log4k-android/images/download.svg) ](https://bintray.com/hadilq/Sheypoor/log4k-android/_latestVersion)

Log4k
---
This is a simple library for logging in Kotlin. It's inspired by the famous Apache Log4j library.

Usage
---
After installation, you just need to call a one letter function like the following.
```kotlin
     d("This is my log")
```
For more advance logging experience you can describe the assumptions of a method with this library as follows.
```kotlin
   fun exampleMethod(foo: Boolean, bar: Boolean, ..., baz: Any?) {
       assumeTrue("Message of AssertionError when this assumption is not satisfied", foo)
           ?.assumeFalse("The same as the above message", bar)
           ...
           ?.assumeNotNull("An other message", baz) {
               // If all the above assumptions get satisfied then this block will be running.
               // Else an Assertion log will be triggered on the proper Appenders.
           }
           // Add more assumptions for the next block
           ?.assumeEquals("A message", foo, bar) {
               // This block will be running if all the above assumptions are true.
           }
   }
```
In this way, a new developer in your team easily will understand the assumptions of the writer of the method. Also, if
each of the assumptions is not satisfied in the runtime, you can log the AssertionError to the Crashlytics or other
services for another review of the method in the future.

Installation
---
Download it in gradle for your Android app like this
```groovy
implementation 'com.log4k:log4k:1.0.0'
implementation 'com.log4k:log4k-android:1.0.0'
```

Then in the `onCreate` method of your `Application` class setup it like this
```kotlin
if (BuildConfig.DEBUG) {
    Log4k.add(Level.Verbose, ".*", AndroidAppender())
    Log4k.add(Level.Verbose, "com\\.log4k\\.sample\\..+", DefaultAppender())
    Log4k.add(Level.Verbose, ".*", DefaultAppender(writer = PrintWriter(File(externalCacheDir, "debug-log.txt"))))
} else {
    Log4k.add(Level.Assert, "com\\.log4k\\.sample\\..+", DefaultAppender(writer = PrintWriter(File(filesDir, "log.txt"))))
}
```
As you can see, you can any kind of appender to handle different kinds of logs. For instance, the `AndroidAppender`
appender log as an ordinary Android `Log`. Or the `DefaultAppender(writer = PrintWriter(File(externalCacheDir, "debug-log.txt")))`
appender log into a file in external cache directory.
