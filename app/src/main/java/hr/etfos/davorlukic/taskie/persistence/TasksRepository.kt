package hr.etfos.davorlukic.taskie.persistence

import hr.etfos.davorlukic.taskie.model.Priority
import hr.etfos.davorlukic.taskie.model.Task

interface TasksRepository {
    fun save(title: String, description: String, priority: Priority): Task
    fun deleteBy(id: Int)
    fun get(id: Int): Task
    fun getAllTasks(): List<Task>
}