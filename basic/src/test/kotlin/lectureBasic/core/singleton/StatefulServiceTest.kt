package lectureBasic.core.singleton

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean

class StatefulServiceTest {

    @Test
    fun statefulServiceSingleton() {
        val ac: ApplicationContext = AnnotationConfigApplicationContext(TestConfig::class.java)
        val statefulService1 = ac.getBean(StatefulService::class.java)
        val statefulService2 = ac.getBean(StatefulService::class.java)

        // ThreadA: A사용자 10000원 주문
        statefulService1.order("userA", 10000)
        // ThreadA: B사용자 20000원 주문
        statefulService1.order("userB", 20000)

        // ThreadA: 사용자A 주문 금액 조회
        val price = statefulService1.price

        // statefulService1과 statefulService2가 같은 객체이다보니 발생한 문제
        assertThat(statefulService1.price).isEqualTo(20000)
    }

    companion object {

        class TestConfig {

            @Bean
            fun statefulService(): StatefulService {
                return StatefulService()
            }
        }
    }
}