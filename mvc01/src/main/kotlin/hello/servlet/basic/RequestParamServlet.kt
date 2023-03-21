package hello.servlet.basic

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 * <p>
 * 2. 동일한 파라미터 전송 가능
 * http://localhost:8080/request-param?username=hello&username=kim&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = ["/request-param"])
class RequestParamServlet : HttpServlet() {

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        println("[전체 파라미터 조회] - start")

        request.parameterNames.iterator()
            .forEachRemaining { paramName ->
                println(paramName.toString() + "=" + request.getParameter(paramName))
            }
        println("[전체 파라미터 조회] - end")
        println()

        println("[단일 파라미터 조회]")
        val username: String = request.getParameter("username")
        println("request.getParameter(username) = $username")

        val age = request.getParameter("age")
        println("request.getParameter(age) = $age")

        println()

        println("[이름이 같은 복수 파라미터 조회]")
        println("request.getParameterValues(username)")
        val usernames = request.getParameterValues("username")
        for (name in usernames) {
            println("username=$name")
        }
        response.writer.write("ok")
    }
}
