package hello.servlet.web.frontcontroller.v4.controller

import hello.servlet.web.frontcontroller.MyView
import hello.servlet.web.frontcontroller.v1.ControllerV1
import hello.servlet.web.frontcontroller.v2.ControllerV2
import hello.servlet.web.frontcontroller.v3.ControllerV3
import hello.servlet.web.frontcontroller.v4.ControllerV4
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerServletV4", urlPatterns = ["/front-controller/v4/*"])
class FrontControllerServletV4 : HttpServlet() {

    private val controllerMap: HashMap<String, ControllerV4> = hashMapOf()

    init {
        controllerMap["/front-controller/v4/members/new-form"] = MemberFormControllerV4()
        controllerMap["/front-controller/v4/members/save"] = MemberSaveControllerV4()
        controllerMap["/front-controller/v4/members"] = MemberListControllerV4()
    }

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        println("FrontControllerServletV4.service")

        val requestURI = request.requestURI

        val controller: ControllerV4? = controllerMap[requestURI]
        if (controller == null) {
            response.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        val paramMap = createParamMap(request)
        val model = hashMapOf <String, Any>()

        val viewName = controller.process(paramMap, model)
        val view = viewResolver(viewName)
        view.render(model, request, response)
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
