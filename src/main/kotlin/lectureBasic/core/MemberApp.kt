package lectureBasic.core

import lectureBasic.core.member.Grade
import lectureBasic.core.member.Member
import lectureBasic.core.member.MemberService
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main(args: Array<String>) {
//    val appConfig = AppConfig()
//    val memberService: MemberService = appConfig.memberService()

    val ac: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
    val memberService: MemberService = ac.getBean("memberService", MemberService::class.java)

    val member = Member(1L, "memberA", Grade.VIP)
    memberService.join(member)

    val findMember = memberService.findMember(1L)

    println("new member = ${member.name}")
    println("findMember = ${findMember.name}")
}
