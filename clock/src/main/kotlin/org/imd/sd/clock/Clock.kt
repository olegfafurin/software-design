package org.imd.sd.clock

import java.time.Instant

/**
 * created by imd on 27.12.2020
 */

interface Clock {
    fun now(): Instant
}