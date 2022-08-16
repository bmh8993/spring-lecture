package lectureBasic.core.lifecycle

import org.junit.jupiter.api.Test
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class BeanLifeCycleTest {

    @Test
    fun lifeCycleTest() {
        val ac: ConfigurableApplicationContext = AnnotationConfigApplicationContext(LifeCycleConfig::class.java)
        val client: NetworkClient = ac.getBean(NetworkClient::class.java)
        ac.close()
    }

    companion object {

        @Configuration
        class LifeCycleConfig {

            // 방법 2
//            @Bean(initMethod = "initBean", destroyMethod = "closeBean")
            @Bean
            fun networkClient(): NetworkClient {
                val networkClient: NetworkClient = NetworkClient()
                networkClient.setUrl("http://hello-spring.dev")
                return networkClient
            }
        }
    }
}