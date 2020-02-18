package todos.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "priorities")
data class Priority(

    // не эффективно, но просто: https://thoughts-on-java.org/jpa-generate-primary-keys/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id val id: Long,

    val name: String,

    val rank: Int
)