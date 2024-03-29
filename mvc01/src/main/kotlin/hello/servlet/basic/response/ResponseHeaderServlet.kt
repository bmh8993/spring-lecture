package hello.servlet.basic.response

import javax.servlet.annotation.WebServlet
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "responseHeaderServlet", urlPatterns = ["/response-header"])
class ResponseHeaderServlet : HttpServlet() {
    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        // [status-line]
        response.status = HttpServletResponse.SC_OK

        // [response-headers]
        response.setHeader("Content-Type", "text/plain")
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate")
        response.setHeader("Pragma", "no-cache")
        response.setHeader("my-header", "hello")

        // [header 편의 메서드]
        content(response)
        cookie(response)
        redirect(response)

        response.writer.println("ok")
    }

    private fun redirect(response: HttpServletResponse) {
        // Status Code 302
        // Location: /basic/hello-form.html

        // response.setStatus(HttpServletResponse.SC_FOUND); // 302
        // response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html")
    }

    private fun content(response: HttpServletResponse) {
        // Content-Type: text/plain;charset-utf-8
        // Content-Length: 2
        // response.setHeader("Content-Type", "text/plain;charset-utf-8");
        response.contentType = "text/plain"
        response.characterEncoding = "utf-8"

        // response.setContentLength(2) 생략시 자동 생성
    }

    private fun cookie(response: HttpServletResponse) {
        // Set-Cookie: myCookie=good; Max-Age=600;
        // response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        val cookie: Cookie = Cookie("myCookie", "good")
        cookie.maxAge = 600 // 600 초

        response.addCookie(cookie)
    }
}
