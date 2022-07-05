package lectureBasic.core.member.service

import lectureBasic.core.member.Member
import lectureBasic.core.member.MemberRepository
import lectureBasic.core.member.MemberService
import lectureBasic.core.member.repository.MemoryMemberRepository

class MemberServiceImpl : MemberService {

    /*
    * MemberServiceImpl은 MemberService(인터페이스)에 의존하고 있음
    * 그렇지만, MemberRepository에 의존하고 있다. 또한, 직접 구현체(MemoryMemberRepository)를 할당하고 있다.
    * 즉, MemberServiceImpl은 추상화(MemberRepository)와 구현체(MemoryMemberRepository)에 모두 의존하고 있다.
    * -> 변경에 매우 취약하다. 즉, DIP를 위반하고 있다.
    * */

    private val memberRepository: MemberRepository = MemoryMemberRepository()

    override fun join(member: Member) {
        memberRepository.save(member)
    }

    override fun findMember(memberId: Long): Member {
        return memberRepository.findById(memberId)
    }
}
