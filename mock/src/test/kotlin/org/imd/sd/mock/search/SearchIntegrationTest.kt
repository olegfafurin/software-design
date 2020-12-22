package org.imd.sd.mock.search

import org.imd.sd.mock.rule.HostReachableRule
import org.imd.sd.mock.rule.HostReachableRule.HostReachable
import org.junit.Assert
import org.junit.ClassRule
import org.junit.Test

/**
 * created by imd on 22.12.2020
 */

@HostReachable(SearchIntegrationTest.HOST)
class SearchIntegrationTest {

    @Test
    fun info() {
        val client = SearchClient(HOST)
        val response = client.processRequest("computer")
        Assert.assertTrue(response.size > 100)
    }

    companion object {
        @get:ClassRule
        @JvmStatic
        val rule = HostReachableRule()

        const val HOST = "api.vk.com"
    }

}