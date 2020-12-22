package org.imd.sd.mock.search

import org.junit.Assert
import org.junit.Test
import java.io.File

/**
 * created by imd on 20.12.2020
 */

class SearchResponseParserTest {

    private val sampleOutput = File("sample_output.txt").readText()

    @Test
    fun parseResponse() {
        val p = ResponseParser()
        val result = p.parseResponse(sampleOutput)
        Assert.assertTrue(result.isNotEmpty())
    }
}