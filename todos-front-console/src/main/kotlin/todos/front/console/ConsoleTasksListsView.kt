package todos.front.console

import todos.usecases.*
import kotlin.reflect.KClass
import kotlin.reflect.KFunction1


class ConsoleTasksListsView(
    private val tasksListsUseCase: TasksListsListUseCase,
    private val deleteTaskList: DeleteTaskListUseCase
) : TasksListsView<ConsoleEvent> {

    private val ops: Map<KClass<*>, KFunction1<TaskListsViewOps, Unit>> = mapOf(
        TaskListsViewOps.ShowNoTaskLists::class to this::showNoTaskLists,
        TaskListsViewOps.ShowTaskLists::class to this::showTaskLists
    )

    override fun show() {
        val op = tasksListsUseCase.showTasksLists()
        (ops[op::class] ?: error("Unsupported task lists view operation: $op")).invoke(op)
    }

    override fun onEvent(e: ConsoleEvent) {
        when {
            e.input.toLowerCase().startsWith("delete") -> {
                val id = e.input.substring(6).trim().toLong()
                val input: Sequence<ConfirmationInput> = sequence {
                    val ans = System.`in`.bufferedReader().readLine()
                    if (ans.toLowerCase() == "yes") {
                        yield(ConfirmationInput.YES)
                    } else {
                        yield(ConfirmationInput.NO)
                    }
                }
                when (val out = deleteTaskList(id, input)) {
                    is DeleteTaskViewOp.TaskLstDeleted -> {
                        println("Task list ${out.id} has been deleted")
                        show()
                    }
                }
            }
            else -> println("Repeat please")
        }
    }

    private fun showNoTaskLists(noTaskListsOp: TaskListsViewOps) {
        println("There are no task lists")
    }

    private fun showTaskLists(op: TaskListsViewOps) {
        (op as? TaskListsViewOps.ShowTaskLists)?.run {
            println("Task lists:")
            taskLists.forEach {
                println("${it.id}: ${it.name}")
            }
        }
    }

}