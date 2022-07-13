package lectureBasic.core

import lectureBasic.core.member.Grade
import lectureBasic.core.member.Member
import lectureBasic.core.member.MemberService
import lectureBasic.core.order.OrderService

fun main(args: Array<String>) {
    val appConfig = AppConfig()
    val memberService: MemberService = appConfig.memberService()
    val orderService: OrderService = appConfig.orderService()

    val memberId = 1L
    val member = Member(memberId, "memberA", Grade.VIP)
    memberService.join(member)

    val order = orderService.createOrder(memberId, "itemA", 10000)

    println("order = $order")
}
