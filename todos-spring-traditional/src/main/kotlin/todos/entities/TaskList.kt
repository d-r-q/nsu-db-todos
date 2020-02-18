package todos.entities

import javax.persistence.*


@Entity(name = "task_lists")
data class TaskList(

    @Id val id: Long?,

    val name: String,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id")
    val tasks: List<Task>
)

fun DefaultTaskList() =
    TaskList(null, "Default", emptyList())
