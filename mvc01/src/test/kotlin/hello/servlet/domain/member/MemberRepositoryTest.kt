package hello.servlet.domain.member

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

class MemberRepositoryTest {

    private val memberRepository = MemberRepository.instance

    @AfterEach
    fun afterEach() {
        memberRepository.clearStore()
    }

    @Test
    fun save() {
        // given
        val member = Member("hello", 20)

        // when
        val savedMember = memberRepository.save(member)

        // then
        val findMember = memberRepository.findById(savedMember.id)
        Assertions.assertThat(findMember).isEqualTo(savedMember)
    }

    @Test
    fun findAll() {
        // given
        val member1 = Member("hello", 20)
        val member2 = Member("hello2", 20)

        memberRepository.save(member1)
        memberRepository.save(member2)

        // when
        val result = memberRepository.findAll()

        // then
        Assertions.assertThat(result.size).isEqualTo(2)
        Assertions.assertThat(result).contains(member1, member2)
    }
}
