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
 * Log levels. Notice we avoid using an 'enum class' to allow people to define their own levels, but current levels are
 * [Verbose], [Debug], [Info], [Warn], [Error] and [Assert].
 */
abstract class Level(val level: Float) {

    /**
     * The [Verbose] Level designates finer-grained informational events than the [Debug] level.
     */
    object Verbose : Level(0F)

    /**
     * The [Debug] Level designates fine-grained informational events that are most useful to debug an application.
     */
    object Debug : Level(1F)

    /**
     * The [Info] level designates informational messages that highlight the progress of the application at
     * coarse-grained level.
     */
    object Info : Level(2F)

    /**
     * The [Warn] level designates potentially harmful situations.
     */
    object Warn : Level(3F)

    /**
     * The [Error] level designates error events that might still allow the application to continue running.
     */
    object Error : Level(4F)

    /**
     * The [Assert] level designates very severe error events that will presumably lead the application to abort or
     * breaking of any of your code assumptions.
     */
    object Assert : Level(5F)
}