package lectureBasic.core.Order

import lectureBasic.core.member.Grade
import lectureBasic.core.member.Member
import lectureBasic.core.member.service.MemberServiceImpl
import lectureBasic.core.order.service.OrderServiceImpl
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class OrderServiceTest {

    val memberService = MemberServiceImpl()
    val orderService = OrderServiceImpl()

    @Test
    fun createOrder() {
        val memberId = 1L
        val member = Member(memberId, "memberA", Grade.VIP)
        memberService.join(member)

        val order = orderService.createOrder(memberId, "itemA", 10000)
        Assertions.assertThat(order.discountPrice).isEqualTo(1000)
    }
}