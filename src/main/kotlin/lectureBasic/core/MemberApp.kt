package lectureBasic.core

import lectureBasic.core.member.Grade
import lectureBasic.core.member.Member
import lectureBasic.core.member.MemberService

fun main(args: Array<String>) {
    val appConfig = AppConfig()

    val memberService: MemberService = appConfig.memberService()
    val member = Member(1L, "memberA", Grade.VIP)
    memberService.join(member)

    val findMember = memberService.findMember(1L)

    println("new member = ${member.name}")
    println("findMember = ${findMember.name}")
}
