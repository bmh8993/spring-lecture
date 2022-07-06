package lectureBasic.core.order.service

import lectureBasic.core.discount.RateDiscountPolicy
import lectureBasic.core.member.repository.MemoryMemberRepository
import lectureBasic.core.order.Order
import lectureBasic.core.order.OrderService

class OrderServiceImpl : OrderService {

    private val memberRepository = MemoryMemberRepository()
//    private val discountPolicy = FixDiscountPolicy()
    private val discountPolicy = RateDiscountPolicy()

    override fun createOrder(memberId: Long, itemName: String, itemPrice: Int): Order {
        val member = memberRepository.findById(memberId)
        val discountPrice = discountPolicy.discount(member, itemPrice)

        return Order(memberId, itemName, itemPrice, discountPrice)
    }
}
