package lectureBasic.core.singleton

import lectureBasic.core.AppConfig
import lectureBasic.core.member.MemberRepository
import lectureBasic.core.member.service.MemberServiceImpl
import lectureBasic.core.order.service.OrderServiceImpl
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ConfigurationSingletonTest {

    @Test
    @Qualifier
    fun configurationTest() {
        val ac: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)

        val memberService: MemberServiceImpl = ac.getBean("memberService", MemberServiceImpl::class.java)
        val orderService: OrderServiceImpl = ac.getBean("orderService", OrderServiceImpl::class.java)
        val memberRepository: MemberRepository = ac.getBean("memberRepository", MemberRepository::class.java)

        val memberRepository1: MemberRepository = memberService.getMemberRepository()
        val memberRepository2: MemberRepository = orderService.getMemberRepository()

        // 아래 세개는 모두 동일한 객체이다. = 싱글톤
        println("memberService -> memberRepository = $memberRepository1")
        println("orderService -> memberRepository = $memberRepository2")
        println("memberRepository = $memberRepository")

        Assertions.assertThat(memberService.getMemberRepository()).isEqualTo(memberRepository)
        Assertions.assertThat(orderService.getMemberRepository()).isEqualTo(memberRepository)
    }

    @Test
    fun configurationDeep() {

        val ac: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
        val bean: AppConfig = ac.getBean(AppConfig::class.java)

        println("bean = ${bean.javaClass}")
    }
}
