package lectureBasic.core.Member

import lectureBasic.core.AppConfig
import lectureBasic.core.member.Grade
import lectureBasic.core.member.Member
import lectureBasic.core.member.MemberService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MemberServiceTest {

    private lateinit var memberService: MemberService
    @BeforeEach
    fun beforeEach() {
        val appConfig = AppConfig()
        memberService = appConfig.memberService()
    }

    @Test
    fun join() {
        // given: 주어진 것
        val member = Member(1L, "memberA", Grade.VIP)

        // when: 무언가를 하면
        memberService.join(member)
        val findMember = memberService.findMember(1L)

        // then: 이렇게 된다.
        Assertions.assertThat(member).isEqualTo(findMember)
    }
}
