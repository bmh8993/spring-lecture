package lectureBasic.core.discount

import lectureBasic.core.member.Grade
import lectureBasic.core.member.Member

class RateDiscountPolicy : DiscountPolicy {

    private val discountPercent: Int = 10

    override fun discount(member: Member, price: Int): Int {
        return when (member.grade) {
            Grade.VIP -> price * discountPercent / 100
            else -> 0
        }
    }
}
