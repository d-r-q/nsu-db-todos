package todos.usecases


class TasksListsListUseCase(
    val taskListRepo: TaskListRepo,
    val tasksListsView: TasksListsView
) {

    fun showTasksLists() {
        tasksListsView.showTasksLists(taskListRepo.getAll().toList())
    }

}