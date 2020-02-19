package examplies.proxy

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy


/**
 * Упрощённый пример реализации ленивой загрузки для классов помеченных аннотацией Entity
 */

/**
 * Вообще @Entity работает на классах, потому что реализована через cglib (https://github.com/cglib/cglib/wiki)
 * Но нам проще сделать это на Jdk Proxy (https://docs.oracle.com/javase/8/docs/technotes/guides/reflection/proxy.html)
 * А принцип работы по сути тот же.
 *
 * Вобщем этот интерфейс нужен только из-за ограничений используемой технологии проксирования.
 */
interface IEntity {
    val id: String
    val name: String
    val refs: List<String>
}

/**
 * Сущность, которую будем проксировать.
 */
class Entity(private val idVal: String, override val name: String, override val refs: List<String>) : IEntity {

    // этот кастомный геттер нужен только для того, чтобы вывести стек вызовов при обращении к нему.
    override val id: String
        get() {
            printTrace()
            return idVal
        }
}

/**
 * Класс который реализует проксирование. У него на вход подаёт ид проксируемого объекта (строки в бд)
 * и ссылка на "базу данных". При создании проксированных объектов реального обращения к БД не происходит,
 * а выполняется оно только при первом обращении к какому-либо полю проксируемого объекта.
 *
 * В реальной жизни, обычно всё-таки идёт обращение в БД за строкой объека и примитивные поля заполняются сразу,
 * а такая техника используется для ленивого получения связанных сущностей.
 */
class EntityProxy(private val id: String, private val db: db) : InvocationHandler {

    // поле инициализация которого выполнится только при первом обращении по средствам вызова лямбды
    private val entity: Entity? by lazy { db.findById(id) }

    override fun invoke(proxy: Any, method: Method, args: Array<out Any>?): Any? {
        println("Handling invocation ${proxy::class.simpleName}.${method.name}($args)")

        // локальная переменная для того, чтобы убедить компилятор, что entity не превратится в null после проверки
        val e = entity ?: throw RuntimeException("Object with id=$id not found")
        return when (method.name) {
            // специальным образом обрабатываем вызов метода toString, чтобы вывести именно объект реализующий прокси, а не проксируемый объект
            // в вызове obj.toString()
            "toString" -> toString()
            // в противном случае просто вызываем найденный метод на нужном объекте с полученными параметрами
            else -> method.invoke(e, *(args ?: arrayOf()))
        }
    }

}

fun printTrace() {
    println("stacktrace:")
    try {
        throw RuntimeException()
    } catch (e: RuntimeException) {
        println(e.stackTrace.drop(1).joinToString("\n") { "  " + it.className + "." + it.methodName })
    }
}

/**
 * Собственно создание прокси - объекта который реализует T (IEntity в нашем случае) и все вызовы делегирует
 * обработчику EntityProxy
 */
inline fun <reified T> createProxy(id: String): T =
    Proxy.newProxyInstance(
        T::class.java.classLoader,
        arrayOf(T::class.java),
        EntityProxy(id, db)
    ) as T

fun main() {
    val proxy: IEntity = createProxy("1")
    println("Proxy: $proxy")
    println("Proxy class: ${proxy::class}")
    println("Proxy id: ${proxy.id}")
}