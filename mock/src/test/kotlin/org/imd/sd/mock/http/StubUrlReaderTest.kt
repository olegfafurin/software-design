package org.imd.sd.mock.http

import com.xebialabs.restito.builder.stub.StubHttp.whenHttp
import com.xebialabs.restito.semantics.Action.status
import com.xebialabs.restito.semantics.Action.stringContent
import com.xebialabs.restito.semantics.Condition.method
import com.xebialabs.restito.semantics.Condition.startsWithUri
import com.xebialabs.restito.server.StubServer
import org.glassfish.grizzly.http.Method
import org.glassfish.grizzly.http.util.HttpStatus
import org.junit.Assert
import org.junit.Test
import java.io.UncheckedIOException

/**
 * created by imd on 22.12.2020
 */

class StubUrlReaderTest {
    private val PORT = 32453
    private val urlReader: UrlReader = UrlReader()

    @Test
    fun readAsText() {
        withStubServer(PORT) { s: StubServer? ->
            whenHttp(s)
                .match(method(Method.GET), startsWithUri("/ping"))
                .then(stringContent("pong"))
            val result = urlReader.readAsText("http://localhost:$PORT/ping")
            Assert.assertEquals(result, "pong\n")
        }
    }

    @Test(expected = UncheckedIOException::class)
    fun readAsTextWithNotFoundError() {
        withStubServer(PORT) { stubServer ->
            whenHttp(stubServer)
                .match(method(Method.GET), startsWithUri("/ping"))
                .then(status(HttpStatus.NOT_FOUND_404))
            urlReader.readAsText("http://localhost:$PORT/ping")
        }
    }

    private fun withStubServer(port: Int, callback: (StubServer?) -> Unit) {
        var stubServer: StubServer? = null
        try {
            stubServer = StubServer(port).run()
            callback(stubServer)
        } finally {
            stubServer?.stop()
        }
    }
}