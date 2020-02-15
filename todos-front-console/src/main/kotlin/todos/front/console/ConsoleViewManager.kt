package todos.front.console

import todos.usecases.TView


class ConsoleViewManager(
    val views: Map<String, TView>,
    currentViewId: String
) {

    var currentView = views[currentViewId]!!

    fun focusView(viewId: String) {
        currentView = views[viewId]!!
    }

}