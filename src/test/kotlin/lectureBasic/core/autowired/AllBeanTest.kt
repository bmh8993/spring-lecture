package lectureBasic.core.autowired

import lectureBasic.core.AutoAppConfig
import lectureBasic.core.discount.DiscountPolicy
import lectureBasic.core.member.Grade
import lectureBasic.core.member.Member
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class AllBeanTest {

    @Test
    fun findAllBean() {

        val ac: ApplicationContext = AnnotationConfigApplicationContext(AutoAppConfig::class.java, DiscountService::class.java)
        val discountService: DiscountService = ac.getBean(DiscountService::class.java)

        val member = Member(1L, "userA", Grade.VIP)

        val discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy")

        Assertions.assertThat(discountService).isInstanceOf(DiscountService::class.java)
        Assertions.assertThat(discountPrice).isEqualTo(1000)
    }

    companion object {

        class DiscountService(
            private var policyMap: Map<String, DiscountPolicy>,
            private val polices: List<DiscountPolicy>
        ) {

            init {
                println(this.polices)
                println(this.policyMap)
            }

            fun discount(member: Member, price: Int, discountPolicyCode: String): Int {
                if (discountPolicyCode !in this.policyMap.keys) {
                    throw Exception("존재하지 않는 discountPolicyCode 입니다.")
                }

                val discountPolicy: DiscountPolicy = policyMap[discountPolicyCode]!!
                return discountPolicy.discount(member, price)
            }
        }
    }
}
