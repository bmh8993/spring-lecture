package lectureBasic.core.singleton

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean

class StatelessServiceTest {

    @Test
    fun statelessServiceSingleton() {
        val ac: ApplicationContext = AnnotationConfigApplicationContext(TestConfig::class.java)
        val statelessService1 = ac.getBean(StatelessService::class.java)
        val statelessService2 = ac.getBean(StatelessService::class.java)

        // ThreadA: A사용자 10000원 주문
        val orderPrice1 = statelessService1.order("userA", 10000)
        // ThreadA: B사용자 20000원 주문
        val orderPrice2 = statelessService2.order("userB", 20000)

        // 상태가 없도록 설계
        assertThat(orderPrice1).isEqualTo(10000)
    }

    companion object {

        class TestConfig {

            @Bean
            fun statelessService(): StatelessService {
                return StatelessService()
            }
        }
    }
}