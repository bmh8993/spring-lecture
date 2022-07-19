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

    @Bean
    fun memberService(): MemberService {
        println("call AppConfig.memberService")
        return MemberServiceImpl(memberRepository())
    }

    @Bean
    fun orderService(): OrderService {
        println("call AppConfig.orderService")
        return OrderServiceImpl(memberRepository(), discountPolicy())
    }

    @Bean
    fun memberRepository(): MemberRepository {
        println("call AppConfig.memberRepository")
        return MemoryMemberRepository()
    }

    @Bean
    fun discountPolicy(): DiscountPolicy {
        println("call AppConfig.discountPolicy")
        return RateDiscountPolicy()
    }
}
