package hello.servlet.web.frontcontroller

class ModelView(
    val viewName: String,
) {
    var model: HashMap<String, Any> = hashMapOf()
        private set

    fun changeModel(model: HashMap<String, Any>) {
        this.model = model
    }
}
