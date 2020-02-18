package todos.dtos

import todos.entities.TaskList


data class TaskListDto(val id: Long?, val name: String, val taskNames: List<String>) {

    constructor(entity: TaskList) : this(entity.id, entity.name, entity.tasks.map { it.name })

}