package hello.servlet.web.frontcontroller.v3.controller

import hello.servlet.web.frontcontroller.MyView
import hello.servlet.web.frontcontroller.v1.ControllerV1
import hello.servlet.web.frontcontroller.v2.ControllerV2
import hello.servlet.web.frontcontroller.v3.ControllerV3
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerServletV3", urlPatterns = ["/front-controller/v3/*"])
class FrontControllerServletV3 : HttpServlet() {

    private val controllerMap: HashMap<String, ControllerV3> = hashMapOf()

    init {
        controllerMap["/front-controller/v3/members/new-form"] = MemberFormControllerV3()
        controllerMap["/front-controller/v3/members/save"] = MemberSaveControllerV3()
        controllerMap["/front-controller/v3/members"] = MemberListControllerV3()
    }

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        println("FrontControllerServletV3.service")

        val requestURI = request.requestURI

        val controller: ControllerV3? = controllerMap[requestURI]
        if (controller == null) {
            response.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        val paramMap = createParamMap(request)

        val mv = controller.process(paramMap)
        val viewName = mv.viewName
        val view = viewResolver(viewName)
        view.render(mv.model, request, response)
    }

    private fun viewResolver(viewName: String): MyView {
        return MyView("/WEB-INF/views/$viewName.jsp")
    }

    private fun createParamMap(request: HttpServletRequest): HashMap<String, String> {
        val paramMap = hashMapOf<String, String>()

        request.parameterNames.iterator()
            .forEachRemaining { paramName ->
                paramMap[paramName] = request.getParameter(paramName)
            }

        return paramMap
    }
}
