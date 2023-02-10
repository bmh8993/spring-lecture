package lectureBasic.core.lifecycle

import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

// 방법 1
// class NetworkClient : InitializingBean, DisposableBean {
class NetworkClient {
    private var url: String? = null

    init {
        println("생성자 호출, url = ${this.url}")
    }

    fun setUrl(url: String) {
        this.url = url
    }

    // 서비스 시작시 호출
    fun connect() {
        println("connect: ${this.url}")
    }

    fun call(message: String) {
        println("call: ${this.url} message = $message")
    }

    // 서비스 종료시 호출
    fun disconnect() {
        println("close: ${this.url}")
    }

//    방법 1
//    override fun afterPropertiesSet() {
//        println("NetworkClient.afterPropertiesSet")
//
//        this.connect()
//        call("초기화 연결 메시지")
//    }
//
//    override fun destroy() {
//        println("NetworkClient.destroy")
//
//        this.disconnect()
//    }

    @PostConstruct
    fun initBean() {
        println("NetworkClient.initBean")

        this.connect()
        call("초기화 연결 메시지")
    }

    @PreDestroy
    fun closeBean() {
        println("NetworkClient.closeBean")

        this.disconnect()
    }
}
