package todos.usecases

import todos.domain.TaskList


interface TaskListRepo {

    fun save(list: TaskList): TaskList

    fun getAll(): Sequence<TaskList>

    fun delete(id: Long)

}