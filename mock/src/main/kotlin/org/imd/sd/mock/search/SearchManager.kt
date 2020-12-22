package org.imd.sd.mock.search

/**
 * created by imd on 20.12.2020
 */

class SearchManager(val client: SearchClient) {

    val SECONDS_IN_HOUR = 60 * 60

    fun search(query: String): List<Int> {
        val posts = client.processRequest(query)
        val currentTimeStamp = System.currentTimeMillis() / 1000L
        val startTimeStamp = currentTimeStamp - 24 * SECONDS_IN_HOUR
        val r = (0..23).map { it * SECONDS_IN_HOUR + startTimeStamp }.map { it..it+SECONDS_IN_HOUR }
        val timeRangeToPosts = posts.groupBy { post -> r.firstOrNull { post.date in it } }
        return r.map { timeRangeToPosts.getOrDefault(it, emptyList()).size }
    }
}