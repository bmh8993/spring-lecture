package lectureBasic.core.scope

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

class PrototypeTest {

    @Test
    fun prototypeBeanFind() {

        val ac: AnnotationConfigApplicationContext = AnnotationConfigApplicationContext(PrototypeBean::class.java)

        val prototypeBean1: PrototypeBean = ac.getBean(PrototypeBean::class.java)
        println("find prototypeBean1")
        val prototypeBean2: PrototypeBean = ac.getBean(PrototypeBean::class.java)
        println("find prototypeBean1")

        println("prototypeBean1 = $prototypeBean1")
        println("prototypeBean2 = $prototypeBean2")

        // 두 객체는 다른 객체이다.
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2)

        ac.close()
    }

    companion object {

        @Scope("prototype")
        class PrototypeBean {

            @PostConstruct
            fun init() {
                println("PrototypeBean.init")
            }

            @PreDestroy
            fun destroy() {
                // Prototype Bean은 스프링 컨테이너의 관리 대상이 아니므로 호출되지 않는다.
                // 클라이언트가 해당 객체를 관리해야하므로 ... 직접 코드를 호출해야한다.
                println("PrototypeBean.destroy")
            }
        }
    }
}
