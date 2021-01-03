package org.imd.sd.clock

import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.collections.set

/**
 * created by imd on 27.12.2020
 */

class EventStatImpl(val clock: Clock) : EventStat {

    private val data: MutableMap<String, Deque<Instant>> = mutableMapOf()
    val SECONDS_IN_HOUR = 60 * 60L

    private fun updateStat(now: Instant) {
        val timeThreshold = now.minusSeconds(SECONDS_IN_HOUR)
        for (entry in data) {
            val eventQueue = data[entry.key]!!
            while (eventQueue.peekFirst().isBefore(timeThreshold))
                data[entry.key]?.removeFirst()
            if (data[entry.key]?.isEmpty() != false)
                data.remove(entry.key)
        }
    }

    override fun incEvent(name: String) {
        val now = clock.now()
        if (name in data)
            data[name]?.addLast(now)
        else
            data[name] = ArrayDeque(listOf(now))
    }

    override fun getAllEventStat(): Map<Instant, Int> {
        updateStat(clock.now())
        val eventsByMinute = mutableMapOf<Instant, Int>()
        for (entry in data) {
            val eventQueue = data[entry.key]!!
            for (event in eventQueue) {
                val eventMinute = event.truncatedTo(ChronoUnit.MINUTES)
                val newValue = eventsByMinute[eventMinute]?.plus(1) ?: 1
                eventsByMinute[eventMinute] = newValue
            }
        }
        return eventsByMinute
    }

    override fun getEventStatByName(name: String): Map<Instant, Int> {
        updateStat(clock.now())
        val eventsByMinute = mutableMapOf<Instant, Int>()
        val eventQueue = data[name] ?: return eventsByMinute
        for (event in eventQueue) {
            val eventMinute = event.truncatedTo(ChronoUnit.MINUTES)
            val newValue = eventsByMinute[eventMinute]?.plus(1) ?: 1
            eventsByMinute[eventMinute] = newValue
        }
        return eventsByMinute
    }

    override fun printStat() {
        val eventsByMinute = getAllEventStat()
        for (minute in eventsByMinute.keys) {
            println("$minute: ${eventsByMinute[minute]}")
        }
    }
}