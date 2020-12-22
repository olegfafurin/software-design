package org.imd.sd.mock.search

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser

/**
 * created by imd on 20.12.2020
 */

class ResponseParser {
    fun parseResponse(response: String): List<WallElement> {
        val responseBody = JsonParser().parse(response) as JsonObject
        val r = (responseBody.get("response") as JsonObject).get("items") as JsonArray
        val elems = mutableListOf<WallElement>()
        for (postJsonRepr in r) {
            val postTyped = postJsonRepr as JsonObject
            elems.add(WallElement(postTyped["id"].asInt, postTyped["date"].asInt, postTyped["text"].asString.take(50)))
        }
        return elems
    }
}