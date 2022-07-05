package lectureBasic.core

import lectureBasic.core.member.Grade
import lectureBasic.core.member.Member
import lectureBasic.core.member.MemberService
import lectureBasic.core.member.service.MemberServiceImpl
import lectureBasic.core.order.OrderService
import lectureBasic.core.order.service.OrderServiceImpl

fun main(args: Array<String>) {

    val memberService: MemberService = MemberServiceImpl()
    val orderService: OrderService = OrderServiceImpl()

    val memberId = 1L
    val member = Member(memberId, "memberA", Grade.VIP)
    memberService.join(member)

    val order = orderService.createOrder(memberId, "itemA", 10000)

    println("order = $order")
}
