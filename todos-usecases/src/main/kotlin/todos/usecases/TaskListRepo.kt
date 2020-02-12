package todos.usecases

import todos.domain.TaskList


interface TaskListRepo {

    fun save(list: TaskList)

}