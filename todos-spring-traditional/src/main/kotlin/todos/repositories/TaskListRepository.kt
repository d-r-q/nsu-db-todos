package todos.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import todos.entities.TaskList


@Repository
interface TaskListRepository : CrudRepository<TaskList, Long>