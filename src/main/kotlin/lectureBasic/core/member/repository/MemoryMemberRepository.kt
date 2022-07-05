package lectureBasic.core.member.repository

import lectureBasic.core.member.Member
import lectureBasic.core.member.MemberRepository

class MemoryMemberRepository : MemberRepository {

    companion object {
        private val store: HashMap<Long, Member> = HashMap()
    }

    override fun save(member: Member) {
        store[member.id] = member
    }

    override fun findById(memberId: Long): Member {
        return store[memberId]!!
    }
}
