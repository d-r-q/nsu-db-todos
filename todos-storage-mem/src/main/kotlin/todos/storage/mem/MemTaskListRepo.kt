package todos.storage.mem

import todos.domain.TaskList
import todos.usecases.TaskListRepo


class MemTaskListRepo : TaskListRepo {

    private val taskLists = ArrayList<TaskList?>()

    override fun save(list: TaskList): TaskList {
        return if (list.id != null) {
            taskLists[list.id!!.toInt()] = list
            list
        } else {
            val savedList = list.copy(id = taskLists.size.toLong())
            taskLists += savedList
            savedList
        }
    }

    override fun getAll(): Sequence<TaskList> =
        taskLists.filterNotNull().asSequence()

    override fun delete(id: Long) {
        taskLists[id.toInt()] = null
    }

}