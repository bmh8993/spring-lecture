package hello.servlet.web.frontcontroller.v4.controller

import hello.servlet.domain.member.MemberRepository
import hello.servlet.web.frontcontroller.v4.ControllerV4

class MemberListControllerV4 : ControllerV4 {
    private val memberRepository = MemberRepository.instance

    override fun process(paramMap: HashMap<String, String>, model: HashMap<String, Any>): String {
        println("MemberListControllerV4.process")
        val members = memberRepository.findAll()

        model["members"] = members

        return "members"
    }
}
