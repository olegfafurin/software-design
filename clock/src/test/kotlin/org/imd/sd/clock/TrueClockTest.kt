package org.imd.sd.clock

import org.junit.Assert
import org.junit.Test

/**
 * created by imd on 02.01.2021
 */

class TrueClockTest {

    @Test
    fun trueClockTest() {
        val dummyClock = TrueClock()
        val dummyStat = EventStatImpl(dummyClock)
        for (i in 1..10) {
            dummyStat.incEvent("event${i % 3}")
        }

        val cnt = dummyStat.getEventStatByName("event0").size
        (0..2).map { dummyStat.getEventStatByName("event$it").size }.forEach {
            Assert.assertEquals(cnt, it)
        }
    }
}