package lectureBasic.core.discount

import lectureBasic.core.annotation.MainDiscountPolicy
import lectureBasic.core.member.Grade
import lectureBasic.core.member.Member

//@Component
//@MainDiscountPolicy
// @Component를 제거하는 대신 DiscountPolicyConfig를 만들어서 빈으로 등록
class RateDiscountPolicy : DiscountPolicy {

    private val discountPercent: Int = 10

    override fun discount(member: Member, price: Int): Int {
        return when (member.grade) {
            Grade.VIP -> price * discountPercent / 100
            else -> 0
        }
    }
}
