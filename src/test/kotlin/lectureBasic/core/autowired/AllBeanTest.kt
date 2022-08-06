package lectureBasic.core.autowired

import lectureBasic.core.AutoAppConfig
import lectureBasic.core.DiscountPolicyConfig
import lectureBasic.core.discount.DiscountService
import lectureBasic.core.member.Grade
import lectureBasic.core.member.Member
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class AllBeanTest {

    @Test
    fun findAllBean() {

        val ac: ApplicationContext = AnnotationConfigApplicationContext(AutoAppConfig::class.java)
        val discountService: DiscountService = ac.getBean(DiscountService::class.java)

        val member = Member(1L, "userA", Grade.VIP)

        val discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy")

        Assertions.assertThat(discountService).isInstanceOf(DiscountService::class.java)
        Assertions.assertThat(discountPrice).isEqualTo(1000)
    }
}
