package todos.usecases


class DeleteTaskListUseCase(private val taskListRepo: TaskListRepo) {

    operator fun invoke(id: Long, input: Sequence<ConfirmationInput>): DeleteTaskViewOp {
        return when (input.first()) {
            ConfirmationInput.YES -> {
                taskListRepo.delete(id)
                DeleteTaskViewOp.TaskLstDeleted(id)
            }
            ConfirmationInput.NO -> DeleteTaskViewOp.NoOp
        }
    }

}

sealed class DeleteTaskViewOp {

    object NoOp : DeleteTaskViewOp()

    class TaskLstDeleted(val id: Long) : DeleteTaskViewOp()

}

enum class ConfirmationInput {

    YES, NO

}