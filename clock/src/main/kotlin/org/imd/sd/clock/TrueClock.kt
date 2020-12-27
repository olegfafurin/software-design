package org.imd.sd.clock

import java.time.Instant

/**
 * created by imd on 27.12.2020
 */

class TrueClock: Clock {
    override fun now(): Instant {
        return Instant.now()
    }
}