package hello.servlet.basic.request

import javax.servlet.annotation.WebServlet
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "requestHeaderServlet", urlPatterns = ["/request-header"])
class RequestHeaderServlet : HttpServlet() {
    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        printStartLine(request)
        printHeaders(request)
        printHeaderUtils(request)
        printEtc(request)
    }

    // start line 정보
    private fun printStartLine(request: HttpServletRequest) {
        println("--- REQUEST-LINE - start ---")
        println("request.getMethod() = " + request.getMethod()) // GET
        println("request.getProtocol() = " + request.getProtocol()) // HTTP/1.1
        println("request.getScheme() = " + request.getScheme()) // http

        println("request.getRequestURL() = " + request.getRequestURL()) // http://localhost:8080/request-header
        println("request.getRequestURI() = " + request.getRequestURI()) // /request-header
        println("request.getQueryString() = " + request.getQueryString()) // username=hi
        println("request.isSecure() = " + request.isSecure()) // https 사용 유무

        println("--- REQUEST-LINE - end ---")
        println()
    }

    private fun printHeaders(request: HttpServletRequest) {
        println("--- Headers - start ---")

        val headerNames = request.headerNames.iterator()
//        while (headerNames.hasNext()) {
//            val next = headerNames.next()
//            println("next = $next")
//        }
        headerNames.forEachRemaining { headerName -> println("headerName = $headerName") }

        println("--- Headers - end ---")
        println()
    }

    // Header 편리한 조회
    private fun printHeaderUtils(request: HttpServletRequest) {
        println("--- Header 편의 조회 start ---")
        println("[Host 편의 조회]")
        println("request.getServerName() = " + request.serverName); // Host 헤더
        println("request.getServerPort() = " + request.serverPort) // Host 헤더  println();
        println("[Accept-Language 편의 조회]")
        request.locales.iterator().forEachRemaining { locale -> println("locale = $locale") }
        println("request.locale = ${request.locale}")
        println()

        println("[cookie 편의 조회]")
        if (request.cookies != null) {
            for (cookie: Cookie in request.cookies) {
                println(cookie.name + ": " + cookie.value)
            }
        }
        println()
        println("[Content 편의 조회]")
        println("request.getContentType() = " + request.contentType)
        println("request.getContentLength() = " + request.contentLength)
        println("request.getCharacterEncoding() = " + request.characterEncoding)

        println("--- Header 편의 조회 end ---")
        println()
    }

    private fun printEtc(request: HttpServletRequest) {
        println("--- 기타 조회 start ---")
        println("[Remote 정보]")
        println("request.getRemoteHost() = " + request.remoteHost)
        println("request.getRemoteAddr() = " + request.remoteAddr)
        println("request.getRemotePort() = " + request.remotePort)
        println()

        println("[Local 정보]")
        println("request.getLocalName() = " + request.localName)
        println("request.getLocalAddr() = " + request.localAddr)
        println("request.getLocalPort() = " + request.localPort)
        println("--- 기타 조회 end ---")
        println()
    }
}
