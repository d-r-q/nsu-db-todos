package todos.fx.lists

import javafx.scene.control.ListView
import kotlinx.coroutines.runBlocking
import todos.fx.client.TodosClient
import tornadofx.View
import tornadofx.listview


class TaskListsView() : View() {

    private val client: TodosClient by di()

    override var root: ListView<String> = run {
        val taskLists = runBlocking {  client.getTaskLists() }
        listview {
            taskLists.forEach { items.add(it.name) }
        }
    }

}