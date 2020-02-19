package examplies.proxy

class Row

object db {

    fun beginTrx() {
        println("Starting trx")
    }

    fun commit() {
        println("Committing trx")
    }

    fun rollback() {
        println("Rollbacking trx")
    }

    fun findById(id: String): Entity? =
        Entity(id, id, listOf(id, id))

    fun exec(sql: String): List<Row> {
        println("Executing $sql")
        return listOf()
    }

}