package lectureBasic.core.discount

import lectureBasic.core.member.Member
import org.springframework.stereotype.Component

@Component
class DiscountService(
    private val policyMap: Map<String, DiscountPolicy>,
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
