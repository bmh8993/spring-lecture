package lectureBasic.core

import lectureBasic.core.member.Grade
import lectureBasic.core.member.Member
import lectureBasic.core.member.MemberService
import lectureBasic.core.order.OrderService
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main(args: Array<String>) {
//    val appConfig = AppConfig()
//    val memberService: MemberService = appConfig.memberService()
//    val orderService: OrderService = appConfig.orderService()

    /**
     * ApplicationContext: 스프링은 이것으로 시작한다. 그러니까 이게 DI 컨테이너다
     * AnnotationConfigApplicationContext: 여기에 @Configuration 을 추가한 클래스를 파라미터로 넣어주면 @Configuration 하위 @Bean 들이 주입된다. 즉, @Bean 들을 관리해준다.
     */
    val ac: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
    val memberService: MemberService = ac.getBean("memberService", MemberService::class.java)
    val orderService: OrderService = ac.getBean("orderService", OrderService::class.java)

    val memberId = 1L
    val member = Member(memberId, "memberA", Grade.VIP)
    memberService.join(member)

    val order = orderService.createOrder(memberId, "itemA", 10000)

    println("order = $order")
}
