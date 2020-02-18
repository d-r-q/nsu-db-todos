package todos

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
class TodosApp

fun main(args: Array<String>) {
    runApplication<TodosApp>(*args)
}