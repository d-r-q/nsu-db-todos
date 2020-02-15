package todos.domain


data class TaskList(
    val id: Long?,
    val name: String,
    val tasks: List<Task>
)

fun DefaultTaskList() =
    TaskList(null, "Default", emptyList())
