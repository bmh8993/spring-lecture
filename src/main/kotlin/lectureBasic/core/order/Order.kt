package lectureBasic.core.order

class Order(

    val memberId: Long,
    val itemName: String,
    val itemPrice: Int,
    val discountPrice: Int
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Order

        if (memberId != other.memberId) return false
        if (itemName != other.itemName) return false
        if (itemPrice != other.itemPrice) return false
        if (discountPrice != other.discountPrice) return false

        return true
    }

    override fun hashCode(): Int {
        var result = memberId.hashCode()
        result = 31 * result + itemName.hashCode()
        result = 31 * result + itemPrice
        result = 31 * result + discountPrice
        return result
    }

    override fun toString(): String {
        return "Order(memberId=$memberId, itemName='$itemName', itemPrice=$itemPrice, discountPrice=$discountPrice)"
    }

    fun calculatePrice(): Int {
        return itemPrice - discountPrice
    }
}