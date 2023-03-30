package hello.servlet.web.frontcontroller.v1.controller

import hello.servlet.domain.member.MemberRepository
import hello.servlet.web.frontcontroller.v1.ControllerV1
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MemberListControllerV1 : ControllerV1{
    private val memberRepository = MemberRepository.instance

    override fun process(request: HttpServletRequest, response: HttpServletResponse) {
        println("MvcMemberListServlet.process")
        val members = memberRepository.findAll()

        request.setAttribute("members", members)

        val viewPath = "/WEB-INF/views/members.jsp"
        val dispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }
}