package lectureBasic.core.common

import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import java.util.UUID
import javax.annotation.PreDestroy

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
class MyLogger {

    private var uuid = UUID.randomUUID().toString()
    private lateinit var requestUrl: String

    init {
        println("[$uuid] request scope bean create $this")
    }

    @PreDestroy
    fun close() {
        println("[$uuid] request scope bean close $this")
    }

    fun setRequestUrl(requestUrl: String) {
        this.requestUrl = requestUrl
    }

    fun log(message: String) {
        println("[$uuid][$requestUrl] $message")
    }
}
