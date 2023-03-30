package hello.servlet.web.frontcontroller.v5

import hello.servlet.web.frontcontroller.ModelView
import hello.servlet.web.frontcontroller.MyView
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerServletV5", urlPatterns = ["/front-controller/v5/*"])
class FrontControllerServletV5 : HttpServlet() {

    private val handlerMappingMap = hashMapOf<String, Any>()
    private val handlerAdapters = mutableListOf<MyHandlerAdapter>()

    init {
        initHandlerMappingMap()
        initHandlerAdapters()
    }

    private fun initHandlerMappingMap() {
        // ControllerV3
        handlerMappingMap["/front-controller/v5/v3/members/new-form"] = MemberFormControllerV3()
        handlerMappingMap["/front-controller/v5/v3/members/save"] = MemberSaveControllerV3()
        handlerMappingMap["/front-controller/v5/v3/members"] = MemberListControllerV3()

        // ControllerV4
        handlerMappingMap["/front-controller/v5/v4/members/new-form"] = MemberFormControllerV4()
        handlerMappingMap["/front-controller/v5/v4/members/save"] = MemberSaveControllerV4()
        handlerMappingMap["/front-controller/v5/v4/members"] = MemberListControllerV4()
    }

    private fun initHandlerAdapters() {
        handlerAdapters.add(ControllerV3HandlerAdapter())
        handlerAdapters.add(ControllerV3HandlerAdapter())
    }

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        println("FrontControllerServletV5.service")

        val handler: Any? = getHandler(request)
        if (handler == null) {
            response.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        val adapter: MyHandlerAdapter = getHandlerAdapter(request)
        val modelView: ModelView = adapter.handle(request, response, handler)
        val view: MyView = viewResolver(modelView.viewName)

        view.render(modelView.model, request, response)
    }

    private fun getHandler(request: HttpServletRequest): Any? {
        val requestURI = request.requestURI

        return handlerMappingMap[requestURI]
    }
    private fun getHandlerAdapter(handler: Any): MyHandlerAdapter {
        for (adapter: MyHandlerAdapter in handlerAdapters) {
            if (adapter.isSupportedBy(handler)) {
                return adapter
            }
        }

        throw IllegalArgumentException("handlerAdapter 를 찾을 수 없습니다. handler=$handler")
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
