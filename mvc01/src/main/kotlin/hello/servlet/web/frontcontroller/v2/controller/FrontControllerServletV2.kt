package hello.servlet.web.frontcontroller.v2.controller

import hello.servlet.web.frontcontroller.v2.ControllerV2
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerServletV2", urlPatterns = ["/front-controller/v2/*"])
class FrontControllerServletV2 : HttpServlet() {

    private val controllerMap: HashMap<String, ControllerV2> = hashMapOf()

    init {
        controllerMap["/front-controller/v2/members/new-form"] = MemberFormControllerV2()
        controllerMap["/front-controller/v2/members/save"] = MemberSaveControllerV2()
        controllerMap["/front-controller/v2/members"] = MemberListControllerV2()
    }

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        println("FrontControllerServletV2.service")

        val requestURI = request.requestURI

        val controller: ControllerV2? = controllerMap[requestURI]
        if (controller == null) {
            response.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        val view = controller.process(request, response)
        view.render(request, response)
    }
}
