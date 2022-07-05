package lectureBasic.core.Member

import lectureBasic.core.member.Grade
import lectureBasic.core.member.Member
import lectureBasic.core.member.service.MemberServiceImpl
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MemberServiceTest {

    private val memberService = MemberServiceImpl()

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
