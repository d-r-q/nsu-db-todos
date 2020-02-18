package todos.front.console

import todos.storage.mem.MemTaskListRepo
import todos.usecases.DeleteTaskListUseCase
import todos.usecases.InitAppUseCase
import todos.usecases.TasksListsListUseCase

fun main() {
    val taskListRepo = MemTaskListRepo()
    val initApp = InitAppUseCase(taskListRepo)
    val showTaskLists = TasksListsListUseCase(taskListRepo)
    val deleteTaskList = DeleteTaskListUseCase(taskListRepo)
    val tasksListsView = ConsoleTasksListsView(showTaskLists, deleteTaskList)
    val viewManager = ConsoleViewManager(mapOf("tlv" to tasksListsView), "tlv")

    initApp()

    viewManager.focusView("tlv")
    while (true) {
        val input = System.`in`.bufferedReader().readLine()
        viewManager.currentView.onEvent(ConsoleEvent(input))
    }

}

