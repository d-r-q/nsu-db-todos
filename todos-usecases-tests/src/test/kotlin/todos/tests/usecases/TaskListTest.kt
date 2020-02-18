package todos.tests.usecases

import io.kotlintest.TestCaseConfig
import io.kotlintest.matchers.sequences.shouldExist
import io.kotlintest.specs.StringSpec
import todos.storage.mem.MemTaskListRepo
import todos.usecases.InitAppUseCase


class TaskListTest : StringSpec() {

    override val defaultTestCaseConfig = TestCaseConfig(invocations = 3)

    init {
        "Init app use case should create default list" {
            // Given tasks repository
            val taskListRepo = MemTaskListRepo()

            val useCase = InitAppUseCase(taskListRepo)

            // When app is initialized
            useCase.invoke()

            // Then default list is created
            taskListRepo.getAll() shouldExist { it.name == "Default" }
        }
    }

}