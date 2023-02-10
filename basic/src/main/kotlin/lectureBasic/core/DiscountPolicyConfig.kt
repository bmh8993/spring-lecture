package lectureBasic.core

import lectureBasic.core.annotation.MainDiscountPolicy
import lectureBasic.core.discount.DiscountPolicy
import lectureBasic.core.discount.FixDiscountPolicy
import lectureBasic.core.discount.RateDiscountPolicy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Component // 원래는 Configuration이라서 @Configuration을 붙여야 하지만 excludeFilters에 걸리므로 @Component 처리
class DiscountPolicyConfig {

    @Bean
    @MainDiscountPolicy
    fun rateDiscountPolicy(): DiscountPolicy {
        return RateDiscountPolicy()
    }

    @Bean
    @Primary // 해당 어노테이션이 없으면 DiscountPolicy의 구현체가 두개 존재하게 된다.
    // 생성자 주입시 동일한 타입이 2개 이상이므로 충돌
    fun fixDiscountPolicy(): DiscountPolicy {
        return FixDiscountPolicy()
    }
}
