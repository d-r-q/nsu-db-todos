package todos.usecases

import todos.domain.TaskList


class TasksListsListUseCase(
    private val taskListRepo: TaskListRepo
) {

    fun showTasksLists(): TaskListsViewOps {
        val taskLists = taskListRepo.getAll().toList()
        return if (taskLists.isNotEmpty()) {
            TaskListsViewOps.ShowTaskLists(taskLists)
        } else {
            TaskListsViewOps.ShowNoTaskLists
        }
    }

}

sealed class TaskListsViewOps {

    object ShowNoTaskLists : TaskListsViewOps()

    class ShowTaskLists(val taskLists: List<TaskList>) : TaskListsViewOps()

}