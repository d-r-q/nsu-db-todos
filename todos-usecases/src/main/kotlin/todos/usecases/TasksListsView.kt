package todos.usecases

import todos.domain.TaskList


interface TasksListsView : TView {

    fun showTasksLists(tasksList: List<TaskList>)

}