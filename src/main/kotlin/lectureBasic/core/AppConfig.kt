package lectureBasic.core

import MemberServiceImpl
import lectureBasic.core.discount.FixDiscountPolicy
import lectureBasic.core.member.MemberService
import lectureBasic.core.member.repository.MemoryMemberRepository
import lectureBasic.core.order.OrderService
import lectureBasic.core.order.service.OrderServiceImpl

class AppConfig {

    fun memberService(): MemberService {
        return MemberServiceImpl(MemoryMemberRepository())
    }

    fun orderService(): OrderService {
        return OrderServiceImpl(MemoryMemberRepository(), FixDiscountPolicy())
    }
}
