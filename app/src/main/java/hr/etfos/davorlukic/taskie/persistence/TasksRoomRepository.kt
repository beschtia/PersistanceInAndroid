package hr.etfos.davorlukic.taskie.persistence

import hr.etfos.davorlukic.taskie.Taskie
import hr.etfos.davorlukic.taskie.db.DaoProvider
import hr.etfos.davorlukic.taskie.model.Priority
import hr.etfos.davorlukic.taskie.model.Task

class TasksRoomRepository : TasksRepository {

    private var db = DaoProvider.getInstance(Taskie.getAppContext())

    private var taskDao = db.taskDao()

    override fun save(title: String, description: String, priority: Priority): Task {
        val task = Task(
            title = title,
            description = description,
            priority = priority
        )
        taskDao.insertTask(task)
        return task
    }

    override fun deleteBy(id: Int) {
        taskDao.deleteTask(get(id))
    }

    override fun get(id: Int): Task = taskDao.getTask(id)

    override fun getAllTasks(): List<Task> = taskDao.getAllTasks()

    fun editTaskTitle(id: Int, newTitle: String){
        taskDao.updateTaskTitle(id, newTitle)
    }

    fun editTaskDescription(id: Int, newDescription: String){
        taskDao.updateTaskDescription(id, newDescription)
    }

    fun editTaskPriority(id: Int, newPriority: Priority){
        taskDao.updateTaskPriority(id, newPriority)
    }

    fun deleteAllTasks(){
        taskDao.deleteAllTasks()
    }

    fun getTasksPrioritySortedAscending(): List<Task> = taskDao.getAllTasksOrderedByPriorityAscending()

    fun getTasksPrioritySortedDescending(): List<Task> = taskDao.getAllTasksOrderedByPriorityDescending()
}