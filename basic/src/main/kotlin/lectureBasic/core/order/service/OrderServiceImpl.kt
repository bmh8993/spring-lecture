package lectureBasic.core.order.service

import lectureBasic.core.annotation.MainDiscountPolicy
import lectureBasic.core.discount.DiscountPolicy
import lectureBasic.core.member.MemberRepository
import lectureBasic.core.order.Order
import lectureBasic.core.order.OrderService
import org.springframework.stereotype.Component

@Component
class OrderServiceImpl(
    private val memberRepository: MemberRepository,
    @MainDiscountPolicy private val discountPolicy: DiscountPolicy
) : OrderService {

//    역할(추상, 인터페이스)와 구현에 모두 의존하는 코드
//    private val memberRepository = MemoryMemberRepository()
//    private val discountPolicy = FixDiscountPolicy()
//    private val discountPolicy = RateDiscountPolicy()

    override fun createOrder(memberId: Long, itemName: String, itemPrice: Int): Order {
        val member = memberRepository.findById(memberId)
        val discountPrice = discountPolicy.discount(member, itemPrice)

        return Order(memberId, itemName, itemPrice, discountPrice)
    }

    // 테스트용
    fun getMemberRepository(): MemberRepository {
        return memberRepository
    }
}
