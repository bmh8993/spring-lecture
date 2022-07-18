package lectureBasic.core.singleton

class StatefulService {

    var price: Int = 0 // 상태를 유지하는 필드
        private set

    fun order(name: String, price: Int) {
        println("name = $name")
        println("price = $price")

        this.price = price
    }
}
