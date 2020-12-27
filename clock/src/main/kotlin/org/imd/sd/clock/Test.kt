package org.imd.sd.clock

import java.time.Instant
import java.time.temporal.ChronoUnit

/**
 * created by imd on 27.12.2020
 */

fun main() {
    val stat = EventStatImpl(TrueClock())
    for (i in 1..10) {
        stat.incEvent("eventType")
        Thread.sleep(1000)
    }
    for (i in 1..10) {
        stat.incEvent("eventType2")
    }
    stat.printStat()

    val dummyClock = SetableClock(Instant.now().truncatedTo(ChronoUnit.DAYS))
    val dummyStat = EventStatImpl(dummyClock)
    for (i in 1..10) {
        dummyStat.incEvent("event${i % 3}")
        dummyClock.now = dummyClock.now.plus(2, ChronoUnit.MINUTES)
    }
    for (i in 0..2) {
        println(dummyStat.getEventStatByName("event$i"))
    }
    dummyStat.printStat()

}