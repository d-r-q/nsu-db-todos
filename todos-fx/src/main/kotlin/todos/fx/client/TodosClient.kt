package todos.fx.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JacksonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get


class TodosClient(private val baseUrl: String = "http://localhost:8080") {

    private val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = JacksonSerializer()
        }
    }

    suspend fun getTaskLists(): List<TaskListDto> {
        return client.get("$baseUrl/tasklist")
    }

}