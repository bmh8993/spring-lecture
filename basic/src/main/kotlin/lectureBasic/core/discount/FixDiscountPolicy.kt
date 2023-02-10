package lectureBasic.core.discount

import lectureBasic.core.member.Grade
import lectureBasic.core.member.Member
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

//@Component
//@Primary
// @Component를 제거하는 대신 DiscountPolicyConfig를 만들어서 빈으로 등록
class FixDiscountPolicy : DiscountPolicy {

    private val discountFixAmount = 1000 // 1000원 할인

    override fun discount(member: Member, price: Int): Int {

        return when (member.grade) {
            Grade.VIP -> discountFixAmount
            else -> 0
        }
    }
}
