package lectureBasic.core

import MemberServiceImpl
import lectureBasic.core.discount.DiscountPolicy
import lectureBasic.core.discount.RateDiscountPolicy
import lectureBasic.core.member.MemberRepository
import lectureBasic.core.member.MemberService
import lectureBasic.core.member.repository.MemoryMemberRepository
import lectureBasic.core.order.OrderService
import lectureBasic.core.order.service.OrderServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    private val memberRepository: MemberRepository = MemoryMemberRepository()
    private val discountPolicy: DiscountPolicy = RateDiscountPolicy()

    @Bean
    fun memberService(): MemberService {
        return MemberServiceImpl(memberRepository)
    }

    @Bean
    fun orderService(): OrderService {
        return OrderServiceImpl(memberRepository, discountPolicy)
    }
}
