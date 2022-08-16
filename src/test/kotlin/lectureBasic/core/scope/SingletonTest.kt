package lectureBasic.core.scope

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

class SingletonTest {

    @Test
    fun singletonBeanFind() {

        val ac: AnnotationConfigApplicationContext = AnnotationConfigApplicationContext(SingleTonBean::class.java)
        val singleTonBean1: SingleTonBean = ac.getBean(SingleTonBean::class.java)
        val singleTonBean2: SingleTonBean = ac.getBean(SingleTonBean::class.java)

        println("singleTonBean1 = $singleTonBean1")
        println("singleTonBean2 = $singleTonBean2")

        // 싱글톤 빈이라 두 객체는 같다.
        Assertions.assertThat(singleTonBean1).isEqualTo(singleTonBean2)

        ac.close()
    }

    companion object {

        @Scope("singleton")
        class SingleTonBean {

            @PostConstruct
            fun init() {
                println("SingleTonBean.init")
            }

            @PreDestroy
            fun destroy() {
                println("SingleTonBean.destroy")
            }
        }
    }
}
