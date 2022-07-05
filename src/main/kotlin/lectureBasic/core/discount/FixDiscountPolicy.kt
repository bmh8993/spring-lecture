package lectureBasic.core.discount

import lectureBasic.core.member.Grade
import lectureBasic.core.member.Member

class FixDiscountPolicy : DiscountPolicy {

    private val discountFixAmount = 1000 // 1000원 할인

    override fun discount(member: Member, price: Int): Int {
        return when (member.grade) {
            Grade.VIP -> discountFixAmount
            else -> 0
        }
    }
}
