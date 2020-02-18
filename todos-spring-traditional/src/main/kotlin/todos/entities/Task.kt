package todos.entities

import javax.persistence.*


@Entity(name = "tasks")
data class Task(

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id val id: Long,

    val name: String,

    val description: String,

    val done: Boolean,

    @OneToOne val priority: Priority,

    @ManyToMany val tags: List<Tag>
)