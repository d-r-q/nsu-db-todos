package todos.fx

import todos.fx.client.TodosClient
import todos.fx.lists.TaskListsView
import tornadofx.App
import tornadofx.DIContainer
import tornadofx.FX
import tornadofx.launch
import kotlin.reflect.KClass

class TodoApp : App(TaskListsView::class)

fun main(args: Array<String>) {
    val client = TodosClient()
    FX.dicontainer = object : DIContainer {
        override fun <T : Any> getInstance(type: KClass<T>): T {
            return when (type.simpleName) {
                "TodosClient" -> client as T
                else -> throw AssertionError("Unknown dependency: ${type.simpleName}")
            }
        }

    }
    launch<TodoApp>(args)
}