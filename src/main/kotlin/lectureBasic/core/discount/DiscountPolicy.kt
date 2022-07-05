package lectureBasic.core.discount

import lectureBasic.core.member.Member

interface DiscountPolicy {

    /**
     * Returns 할인 대상 금액
     */
    fun discount(member: Member, price: Int): Int
}
