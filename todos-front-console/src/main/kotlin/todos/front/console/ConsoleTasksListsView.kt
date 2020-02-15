package todos.front.console

import todos.domain.TaskList
import todos.usecases.TasksListsView


class ConsoleTasksListsView : TasksListsView {

    override fun showTasksLists(tasksList: List<TaskList>) {
        println("Task lists:")
        tasksList.forEach {
            println(it.toString())
        }
    }

}