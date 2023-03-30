package hello.servlet.web.frontcontroller.v5.adapter

import hello.servlet.web.frontcontroller.ModelView
import hello.servlet.web.frontcontroller.v4.ControllerV4
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ControllerV4HandlerAdapter : MyHandlerAdapter {
    override fun isSupportedBy(handler: Any): Boolean {
        return handler is ControllerV4
    }

    override fun handle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): ModelView {
        val controller: ControllerV4 = handler as ControllerV4

        val paramMap = createParamMap(request)
        val model = hashMapOf<String, Any>()

        val viewName: String = controller.process(paramMap, model)
        val modelView = ModelView(viewName)

        modelView.changeModel(model)

        return modelView
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
