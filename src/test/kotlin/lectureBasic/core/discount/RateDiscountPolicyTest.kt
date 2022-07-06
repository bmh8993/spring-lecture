package lectureBasic.core.discount

import lectureBasic.core.member.Grade
import lectureBasic.core.member.Member
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class RateDiscountPolicyTest {

    val discountPolicy: RateDiscountPolicy = RateDiscountPolicy()

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    fun vipDiscountTenPercent() {
        // given
        val member = Member(1L, "memberVIP", Grade.VIP)
        // when
        val discount = discountPolicy.discount(member, 10000)
        // then
        assertThat(discount).isEqualTo(1000)
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    fun notVipDiscountZeroPercent() {
        // given
        val member = Member(1L, "memberBASIC", Grade.BASIC)
        // when
        val discount = discountPolicy.discount(member, 10000)
        // then
        assertThat(discount).isEqualTo(0)
    }
}
