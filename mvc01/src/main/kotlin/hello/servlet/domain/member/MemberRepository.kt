package hello.servlet.domain.member

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
class MemberRepository private constructor() {
    fun save(member: Member): Member {
        member.id = ++sequence
        store[member.id] = member
        return member
    }

    fun findById(id: Long?): Member? {
        return store[id]
    }

    fun findAll(): List<Member> {
        return ArrayList(store.values)
    }

    fun clearStore() {
        store.clear()
    }

    companion object {
        private val store: MutableMap<Long?, Member> = HashMap() // static 사용
        private var sequence = 0L // static 사용
        val instance = MemberRepository()
    }
}
