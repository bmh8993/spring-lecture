package lectureBasic.core.scan.filter

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

class ComponentFilterAppConfigTest {

    @Test
    @DisplayName("BeanA는 빈에 등록되지만, BeanB는 빈에 등록되지 않는다.")
    fun filterScan() {
        val ac: ApplicationContext = AnnotationConfigApplicationContext(ComponentFilterAppConfig::class.java)

        val beanA: BeanA = ac.getBean("beanA", BeanA::class.java)
        assertThat(beanA).isNotNull

        Assertions.assertThrows(NoSuchBeanDefinitionException::class.java) {
            ac.getBean("beanB", BeanB::class.java)
        }
    }

    companion object {

        @Configuration
        @ComponentScan(
            includeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION, classes = [MyIncludeComponent::class])],
            excludeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION, classes = [MyExcludeComponent::class])],
        )
        class ComponentFilterAppConfig
    }
}