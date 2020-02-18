package todos.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import todos.dtos.TaskListDto
import todos.services.TaskListService


@RestController
@RequestMapping("tasklist")

class TaskListController

@Autowired constructor(
    val taskListService: TaskListService
) {

    @GetMapping
    fun getAll(): List<TaskListDto> {
        return taskListService.getAll()
    }

}