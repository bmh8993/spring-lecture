package hello.servlet.basic

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.SingletonSupport
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.util.StreamUtils
import java.nio.charset.StandardCharsets
import javax.servlet.ServletInputStream
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = ["/request-body-json"])
class RequestBodyJsonServlet : HttpServlet() {

    private val mapper = jacksonObjectMapper()

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        val inputStream: ServletInputStream = request.inputStream
        val messageBody: String = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)

        println("messageBody = $messageBody")

        val helloData: HelloData = mapper.readValue(messageBody, HelloData::class.java)

        println("helloData = $helloData")

        response.writer.write("ok")
    }
}
