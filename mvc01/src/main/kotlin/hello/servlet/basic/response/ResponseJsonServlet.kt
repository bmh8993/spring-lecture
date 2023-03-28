package hello.servlet.basic.response

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import hello.servlet.basic.HelloData
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "responseJsonServlet", urlPatterns = ["/response-json"])
class ResponseJsonServlet : HttpServlet() {

    private val objectMapper = jacksonObjectMapper()
    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        // Content-Type: application/json
        response.contentType = "application/json"
        response.characterEncoding = "utf-8"

        val helloData = HelloData("bae", 20)

        // {"username": "bae", "age": 20}
        val result = objectMapper.writeValueAsString(helloData)
        response.writer.write(result)
    }
}