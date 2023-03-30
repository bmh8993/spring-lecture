package hello.servlet.web.frontcontroller

class ModelView(
    val viewName: String,
) {
    val model = hashMapOf<String, Any>()
}
