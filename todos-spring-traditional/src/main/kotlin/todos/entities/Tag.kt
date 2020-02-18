package todos.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "tags")
data class Tag(

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id val id: Long,

    val name: String
)