import org.imd.sd.mock.search.SearchClient
import org.imd.sd.mock.search.SearchManager

/**
 * created by imd on 20.12.2020
 */

fun main() {
    val client = SearchClient("api.vk.com")
    val manager = SearchManager(client)
    val t = manager.search("cyclone")
    println(t.joinToString(prefix = "[", postfix = "]", separator = ", "))
}