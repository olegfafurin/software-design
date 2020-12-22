package org.imd.sd.mock.search

import org.imd.sd.mock.search.SearchClient
import org.imd.sd.mock.search.SearchManager
import org.imd.sd.mock.search.WallElement
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * created by imd on 22.12.2020
 */

class SearchManagerTest {

    @Mock
    private lateinit var client: SearchClient

    private lateinit var manager: SearchManager

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        manager = SearchManager(client)
    }

    @Test
    fun getFrequencies() {
        val query = "forest"
        Mockito.`when`(client.processRequest(query))
            .thenReturn(sampleAnswer())
        val counts = manager.search(query)
        Assert.assertEquals(List(24) { if (it < 21) 0 else 1 }, counts)
    }

    fun sampleAnswer(): List<WallElement> {
        val t = System.currentTimeMillis() / 1000L
        return listOf(
            WallElement(1, (t - 60).toInt(), "text"),
            WallElement(1, (t - 30 - 3600).toInt(), "blahblah"),
            WallElement(1, (t - 20 - 7200).toInt(), "input")
        )
    }

}