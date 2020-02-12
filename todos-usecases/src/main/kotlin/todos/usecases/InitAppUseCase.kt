package todos.usecases

import todos.domain.DefaultTaskList


class InitAppUseCase(
    private val taskListRepo: TaskListRepo
) {

    fun initApp() {
        val defaultList = DefaultTaskList()
        taskListRepo.save(defaultList)
    }

}