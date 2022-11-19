package lectureBasic.core.common

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.util.UUID
import javax.annotation.PreDestroy

@Component
@Scope(value = "request")
class MyLogger {

    private lateinit var uuid: String
    private lateinit var requestUrl: String

    init {
        uuid = UUID.randomUUID().toString()
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
