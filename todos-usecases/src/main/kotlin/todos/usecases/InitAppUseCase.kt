package todos.usecases

import todos.domain.DefaultTaskList


class InitAppUseCase(
    private val taskListRepo: TaskListRepo
) {

    operator fun invoke() {
        val defaultList = DefaultTaskList()
        taskListRepo.save(defaultList)
    }

}