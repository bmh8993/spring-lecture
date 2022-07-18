package lectureBasic.core.singleton

/**
 * 1 서버가 돌아가면서 스태틱 영역에 instance를 생성해서 올린다. 이때, 생성자도 private이다.
 * 2 이 객체 인스턴스가 필요하면 오직 getInstance를 통해서만 조회할 수 있다. 이 메서드를 호출하면 항상 같은 인스턴스를 반환한다.
 * 3 딱 1개의 객체 인스턴스만 존재해야하므로, 생성자를 private로 막아서 혹시라도 외부에서 객체 인스턴스가 생성되는 것을 막는다.(java의 경우 new로 객체생성하는 것을 막는다!)
 */
class SingletonService private constructor() {

    companion object {

        // static이기 때문에 클래스레벨에 올라가기 때문에 하나만 존재함
        private val instance = SingletonService()

        fun getInstance(): SingletonService {
            return instance
        }
    }

    fun logic() {
        println("싱글톤 객체 로직 호출")
    }
}
