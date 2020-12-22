package org.imd.sd.mock.http

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.UncheckedIOException
import java.net.MalformedURLException
import java.net.URL

/**
 * created by imd on 20.12.2020
 */

class UrlReader {

    fun readAsText(sourceUrl: String): String {
        val url = toUrl(sourceUrl)
        try {
            BufferedReader(InputStreamReader(url.openStream())).use { `in` ->
                val buffer = StringBuilder()
                var inputLine: String?
                while (`in`.readLine().also { inputLine = it } != null) {
                    buffer.append(inputLine)
                    buffer.append("\n")
                }
                return buffer.toString()
            }
        } catch (e: IOException) {
            throw UncheckedIOException(e)
        }
    }

    fun toUrl(url: String): URL {
        return try {
            URL(url)
        } catch (e: MalformedURLException) {
            throw RuntimeException("Invalid url: $url")
        }
    }
}