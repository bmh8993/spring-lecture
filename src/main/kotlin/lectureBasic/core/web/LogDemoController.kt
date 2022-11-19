package lectureBasic.core.web

import lectureBasic.core.common.MyLogger
import lectureBasic.core.logdemo.LogDemoService
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest

@Controller
class LogDemoController(
    private val logDemoService: LogDemoService,
    private val myLoggerProvider: ObjectProvider<MyLogger>
) {
    @RequestMapping("log-demo")
    @ResponseBody
    fun logDemo(request: HttpServletRequest): String {
        val requestUrl = request.requestURL.toString()
        val myLogger = myLoggerProvider.getObject()

        myLogger.setRequestUrl(requestUrl)

        myLogger.log("controller test")
        logDemoService.logic("testId")

        return "OK"
    }
}