package lectureBasic.core.beanfind

import lectureBasic.core.discount.DiscountPolicy
import lectureBasic.core.discount.FixDiscountPolicy
import lectureBasic.core.discount.RateDiscountPolicy
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoUniqueBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean

class ApplicationContextExtendsFindTest {

    val ac: AnnotationConfigApplicationContext = AnnotationConfigApplicationContext(TestConfig::class.java)

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다.")
    fun findBeanByParentTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException::class.java) {
            ac.getBean(DiscountPolicy::class.java)
        }
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    fun findBeanByParentTypeName() {
        val rateDiscountPolicy: DiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy::class.java)
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy::class.java)
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    fun findBeanBySubType() {
        val rateDiscountPolicy: RateDiscountPolicy = ac.getBean("rateDiscountPolicy", RateDiscountPolicy::class.java)
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy::class.java)
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    fun findAllBeanByParentType() {
        val beansOfType: MutableMap<String, DiscountPolicy> = ac.getBeansOfType(DiscountPolicy::class.java)
        assertThat(beansOfType.size).isEqualTo(2)
        for (key in beansOfType.keys) {
            println("key = $key")
            println("value = ${beansOfType[key]}")
        }
    }

    @Test
    @DisplayName("부모 타임으로 모두 조회하기 - Any") // 스프링 내부적으로 사용하고 있는 모든 빈이 나온다.
    fun findAllBeanByAnyType() {
        val beansOfType: MutableMap<String, Any> = ac.getBeansOfType(Any::class.java)
        for (key in beansOfType.keys) {
            println("key = $key")
            println("value = ${beansOfType[key]}")
        }
    }

    companion object {
        class TestConfig {

            @Bean
            fun rateDiscountPolicy(): DiscountPolicy {
                return RateDiscountPolicy()
            }

            @Bean
            fun fixDiscountPolicy(): DiscountPolicy {
                return FixDiscountPolicy()
            }
        }
    }
}
