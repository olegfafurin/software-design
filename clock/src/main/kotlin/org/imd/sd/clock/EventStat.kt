package org.imd.sd.clock

import java.time.Instant

/**
 * created by imd on 27.12.2020
 */

interface EventStat {
    fun incEvent(name: String)

    fun getAllEventStat(): Map<Instant, Int>

    fun getEventStatByName(name: String): Map<Instant, Int>

    fun printStat()
}