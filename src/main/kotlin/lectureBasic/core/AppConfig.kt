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

    /**
     * before
     * - 메서드의 파라미터로 객체를 직접 생성했다. 그렇다보니 중복이 발생했다. ex. MemoryMemberRepository
     * - 그렇기때문에 구현을 바꾸게 되면 하나씩 전체를 바꿔주어야했다.(=구현이 잘 보이지 않는다.)
     *
     * after
     * - 변수에 객체를 할당하여 해당 변수를 파라미터로 넘겨주었다. 구현을 바꾸게 된다면 해당 객체만 교체하면 된다.
     */
    private val memberRepository: MemberRepository = MemoryMemberRepository()
//    private val discountPolicy: DiscountPolicy = FixDiscountPolicy()
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
