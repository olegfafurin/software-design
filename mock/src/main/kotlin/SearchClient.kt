/**
 * created by imd on 20.12.2020
 */

class SearchClient(
    val host: String,
    val urlReader: UrlReader = UrlReader(),
    val parser: ResponseParser = ResponseParser()
) {

    fun processRequest(query: String): List<WallElement> {
        return parser.parseResponse(urlReader.readAsText(buildUrl(query)))
    }

    fun buildUrl(q: String) = "https://$host/method/newsfeed.search?q=$q&extended=0&v=5.126&count=200&access_token=${System.getenv("VK_API_SERVICE_KEY")}"
}