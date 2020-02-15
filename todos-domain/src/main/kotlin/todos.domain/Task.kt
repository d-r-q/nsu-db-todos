package todos.domain


data class Task(
    val id: Long,
    val name: String,
    val done: Boolean,
    val priority: Priority,
    val tags: List<Tag>
)