package todos.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import todos.dtos.TaskListDto
import todos.repositories.TaskListRepository
import todos.services.TaskListService


@Service
class TaskListServiceImpl

@Autowired constructor(
    val taskListRepo: TaskListRepository
) : TaskListService {

    @Transactional(readOnly = true)
    override fun getAll(): List<TaskListDto> {
        return taskListRepo.findAll().map { TaskListDto(it) }
    }

}