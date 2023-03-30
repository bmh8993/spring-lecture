package hello.servlet.web.frontcontroller.v5.adapter

import hello.servlet.web.frontcontroller.ModelView
import hello.servlet.web.frontcontroller.v3.ControllerV3
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ControllerV3HandlerAdapter : MyHandlerAdapter {

    override fun isSupportedBy(handler: Any): Boolean {
        return handler is ControllerV3
    }

    override fun handle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): ModelView {
        val controller: ControllerV3 = handler as ControllerV3
        val paramMap = createParamMap(request)

        return controller.process(paramMap)
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
