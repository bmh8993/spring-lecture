package hello.servlet.basic

import org.springframework.util.StreamUtils
import java.nio.charset.StandardCharsets
import javax.servlet.ServletInputStream
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "requestBodyStringServlet", urlPatterns = ["/request-body-string"])
class RequestBodyStringServlet : HttpServlet() {
    override fun service(requst: HttpServletRequest, response: HttpServletResponse) {
        // body의 내용을 바이트로 받음
        val inputStream: ServletInputStream = requst.inputStream

        // 바이트를 문자열로 변환
        val messageBody: String = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)

        println("messageBody = $messageBody")

        response.writer.write("ok")
    }
}
