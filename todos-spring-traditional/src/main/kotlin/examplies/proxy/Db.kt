package examplies.proxy

// Упрощённый стаб одновременно и для Connection и для EntityManager
object db {

    // Connection
    fun beginTrx() {
        println("Starting trx")
    }

    fun commit() {
        println("Committing trx")
    }

    fun rollback() {
        println("Rollbacking trx")
    }

    // EntityManager
    fun findById(id: String): ExampleEntity? =
        ExampleEntity(id, id, listOf(id, id))

    fun exec(jpql: String): List<Any> {
        println("Executing $jpql")
        return listOf(User(1, "1"), User(2, "2"))
    }

}