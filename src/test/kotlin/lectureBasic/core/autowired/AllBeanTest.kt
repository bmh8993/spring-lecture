package lectureBasic.core.autowired

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

        val ac: ApplicationContext = AnnotationConfigApplicationContext(DiscountService::class.java)
        val discountService: DiscountService = ac.getBean(DiscountService::class.java)

        val member = Member(1L, "userA", Grade.VIP)

        val discountPrice = discountService.discount(member, 10000, "FixDiscountPolicy")

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

            fun discount(member: Member, price: Int, discountCode: String): Int {
                val discountPolicy: DiscountPolicy = policyMap[discountCode] ?: return 0
                return discountPolicy.discount(member, price)
            }
        }
    }
}
