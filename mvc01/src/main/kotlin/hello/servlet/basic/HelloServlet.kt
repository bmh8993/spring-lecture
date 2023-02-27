package hello.servlet.basic

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "helloServlet", urlPatterns = ["/hello"])
class HelloServlet : HttpServlet() {
    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        println("HelloServlet.service")
        println("request = $request") // HttpServletRequest, HttpServletResponse는 인터페이스이고, was별로 해당 인터페이스를 사용해서 구현체를 작성한다.
        println("response = $response")

        // 쿼리 파라미터 조회
        val username: String? = request?.getParameter("username")
        println("username = $username")

        // response에 응답 추가하기
        response?.contentType = "text/plain"
        response?.characterEncoding = "utf-8"
        response?.writer?.write("hello $username")
    }
}
