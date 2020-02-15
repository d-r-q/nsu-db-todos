package todos.front.console

import todos.storage.mem.MemTaskListRepo
import todos.usecases.InitAppUseCase
import todos.usecases.TasksListsListUseCase

fun main() {
    val taskListRepo = MemTaskListRepo()
    val useCase = InitAppUseCase(taskListRepo)
    val tasksListsView = ConsoleTasksListsView()
    val tasksListsUseCase = TasksListsListUseCase(
        taskListRepo,
        tasksListsView
    )
    val viewManager = ConsoleViewManager(mapOf("tlv" to tasksListsView), "tlv")

    useCase.initApp()

    while (true) {
        viewManager.currentView.show()
        val input = System.`in`.bufferedReader().readLine()
        viewManager.currentView.onEvent(ConsoleEvent(input))
    }

}

