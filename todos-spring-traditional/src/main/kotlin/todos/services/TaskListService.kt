package todos.services

import todos.dtos.TaskListDto


interface TaskListService {
    fun getAll(): List<TaskListDto>
}