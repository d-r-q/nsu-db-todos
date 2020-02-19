package examplies.proxy

import java.lang.IllegalStateException
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * Пример принцыпа реализации аннотации @Transactional
 */

interface Service {

    fun deleteAll(cascade: String)

}

class ServiceImpl : Service {

    override fun deleteAll(cascade: String) {
        println("Deleting all")
        if (cascade == "false") {
            throw IllegalStateException("Reference integrity violation")
        }
    }

}

class TrxProxy(private val delegate: Any, private val db: db) : InvocationHandler {

    override fun invoke(proxy: Any, method: Method, args: Array<out Any>?): Any? {
        // начинаем транзакцию
        db.beginTrx()
        try {
            // вызываем метод, который должен быть транзакционным
            return method.invoke(delegate, *(args ?: arrayOf()))
                // если он завершился успешно, то коммитим транзакцию
                .also { db.commit() }

        } catch (e: Exception) {
            // в противно случае ролбэчим транзакцию
            db.rollback()
            // и перебрасываем оригинальное исключение, либо его пирчину если она есть
            throw e.cause ?: e
        }
    }

}

inline fun <reified T> createTrxProxy(delegate: Any, db: db): T {

    return Proxy.newProxyInstance(
        T::class.java.classLoader,
        arrayOf(T::class.java),
        TrxProxy(delegate, db)
    ) as T

}

fun main() {
    val service = ServiceImpl()
    val trxService = createTrxProxy<Service>(service, db)

    service.deleteAll("true")

    println()

    trxService.deleteAll("true")

    println()

    trxService.deleteAll("false")
}