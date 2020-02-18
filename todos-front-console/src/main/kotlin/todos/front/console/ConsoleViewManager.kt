package todos.front.console

import todos.usecases.TView


class ConsoleViewManager(
    val views: Map<String, TView<ConsoleEvent>>,
    currentViewId: String
) {

    var currentView = views[currentViewId]!!

    fun focusView(viewId: String) {
        currentView = views[viewId] ?: error("Unknown viewId: $viewId")
        currentView.show()
    }

}