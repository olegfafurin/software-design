package org.imd.sd.clock

import org.junit.Test
import org.junit.Assert
import java.time.Instant
import java.time.temporal.ChronoUnit
import kotlin.test.assertTrue

/**
 * created by imd on 02.01.2021
 */

class SettableClockTest {

    @Test
    fun settableClockTest() {
        val startTime = Instant.now().truncatedTo(ChronoUnit.DAYS)
        val dummyClock = SettableClock(startTime)
        val dummyStat = EventStatImpl(dummyClock)
        for (i in 1..12) {
            dummyStat.incEvent("event${i % 3}")
            dummyClock.now = dummyClock.now.plus(2, ChronoUnit.MINUTES)
        }

        for (i in 0..2) {
            dummyStat.getEventStatByName("event$i").values.forEach {
                Assert.assertEquals(1, it)
            }
        }
    }

    @Test
    fun settableClockTimeTest() {
        val startTime = Instant.now().truncatedTo(ChronoUnit.DAYS)
        val dummyClock = SettableClock(startTime)
        val dummyStat = EventStatImpl(dummyClock)

        for (i in 1..10) {
            dummyStat.incEvent("event$i")
            dummyClock.now = dummyClock.now.plus(2, ChronoUnit.MINUTES)
        }

        val wholeStat = dummyStat.getAllEventStat().keys.sorted()
        for (i in 0..9) {
            Assert.assertEquals(wholeStat[i], startTime.plus(2L * i, ChronoUnit.MINUTES))
        }
    }

}