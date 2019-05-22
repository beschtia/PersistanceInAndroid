package hr.etfos.davorlukic.taskie.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import hr.etfos.davorlukic.taskie.model.Priority
import hr.etfos.davorlukic.taskie.model.Task


@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getAllTasks(): List<Task>

    @Query("SELECT * FROM Task ORDER BY priority")
    fun getAllTasksOrderedByPriorityAscending(): List<Task>

    @Query("SELECT * FROM Task ORDER BY priority DESC")
    fun getAllTasksOrderedByPriorityDescending(): List<Task>

    @Insert(onConflict = IGNORE)
    fun insertTask(task: Task)

    @Query("SELECT * FROM Task WHERE id = :taskId" )
    fun getTask(taskId: Int): Task

    @Delete
    fun deleteTask(task: Task)

    @Query("UPDATE Task SET title= :taskTitle WHERE id = :taskId")
    fun updateTaskTitle(taskId: Int, taskTitle: String)

    @Query("UPDATE Task SET description= :taskDescription WHERE id = :taskId")
    fun updateTaskDescription(taskId: Int, taskDescription: String)

    @Query("UPDATE Task SET priority= :taskPriority WHERE id = :taskId")
    fun updateTaskPriority(taskId: Int, taskPriority: Priority)

    @Query("DELETE from Task")
    fun deleteAllTasks()
}