package lectureBasic.core.logdemo

import lectureBasic.core.common.MyLogger
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Service

@Service
class LogDemoService(
//    private val myLoggerProvider: ObjectProvider<MyLogger>
    private val myLogger: MyLogger
) {
    fun logic(id: String) {
//        val myLogger = myLoggerProvider.getObject()
        myLogger.log("service Id = $id")
    }
}
