package lectureBasic.core.scan

import lectureBasic.core.AutoAppConfig
import lectureBasic.core.member.MemberService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class AutoAppConfigTest {

    @Test
    fun basicScan() {

        val ac: ApplicationContext = AnnotationConfigApplicationContext(AutoAppConfig::class.java)

        val memberService: MemberService = ac.getBean(MemberService::class.java)

        Assertions.assertThat(memberService).isInstanceOf(MemberService::class.java)
    }
}