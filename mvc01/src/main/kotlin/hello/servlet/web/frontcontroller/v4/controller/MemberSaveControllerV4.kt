package hello.servlet.web.frontcontroller.v4.controller

import hello.servlet.domain.member.Member
import hello.servlet.domain.member.MemberRepository
import hello.servlet.web.frontcontroller.v4.ControllerV4

class MemberSaveControllerV4 : ControllerV4 {
    private val memberRepository = MemberRepository.instance

    override fun process(paramMap: HashMap<String, String>, model: HashMap<String, Any>): String {
        val username: String = paramMap["username"].toString()
        val age: Int = paramMap["age"]!!.toInt()
        val member = Member(username, age)
        memberRepository.save(member)

        model["member"] = member

        return "save-result"
    }
}
