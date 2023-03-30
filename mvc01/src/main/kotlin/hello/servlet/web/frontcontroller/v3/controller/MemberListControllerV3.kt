package hello.servlet.web.frontcontroller.v3.controller

import hello.servlet.domain.member.MemberRepository
import hello.servlet.web.frontcontroller.ModelView
import hello.servlet.web.frontcontroller.MyView
import hello.servlet.web.frontcontroller.v1.ControllerV1
import hello.servlet.web.frontcontroller.v2.ControllerV2
import hello.servlet.web.frontcontroller.v3.ControllerV3
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MemberListControllerV3 : ControllerV3 {
    private val memberRepository = MemberRepository.instance

    override fun process(paramMap: Map<String, String>): ModelView {
        println("MemberListControllerV3.process")
        val members = memberRepository.findAll()

        val mv = ModelView("members")
        mv.model["members"] = members

        return mv
    }
}
