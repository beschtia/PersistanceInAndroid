package hr.etfos.davorlukic.taskie.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.etfos.davorlukic.taskie.R
import hr.etfos.davorlukic.taskie.ui.SwipeToDeleteCallback
import hr.etfos.davorlukic.taskie.common.*
import hr.etfos.davorlukic.taskie.model.Task
import hr.etfos.davorlukic.taskie.persistence.TasksRoomRepository
import hr.etfos.davorlukic.taskie.ui.interfaces.OptionsMenuListener
import hr.etfos.davorlukic.taskie.ui.activities.ContainerActivity
import hr.etfos.davorlukic.taskie.ui.adapters.TaskAdapter
import hr.etfos.davorlukic.taskie.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tasks.*

class TasksFragment : BaseFragment(), AddTaskFragmentDialog.TaskAddedListener,
    OptionsMenuListener {
    private val repository = TasksRoomRepository()
    private var data = repository.getAllTasks()
    private val adapter by lazy { TaskAdapter {onItemSelected(it)} }
    override fun getLayoutResourceId() = R.layout.fragment_tasks

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initListeners()
        refreshTasks()
    }

    override fun onResume() {
        super.onResume()
        setData(repository.getAllTasks())
        refreshTasks()
    }

    private fun initUi() {
        progress.visible()
        noData.visible()
        tasksRecyclerView.layoutManager = LinearLayoutManager(context)
        tasksRecyclerView.adapter = adapter
        val swipeHandler = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                showDeleteTaskAlertDialog(position)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView)
    }

    private fun showDeleteTaskAlertDialog(position: Int){
        AlertDialog.Builder(activity as Context)
            .setTitle(R.string.alertDeleteTaskTitleText)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton(R.string.alertPositiveButtonText) { _, _ -> repository.deleteBy(adapter.getTaskID(position))
                adapter.removeAt(position) }
            .setNegativeButton(R.string.alertNegativeButtonText) { _, _ -> refreshTasks()}
            .show()
    }

    private fun initListeners() {
        addTask.setOnClickListener { addTask() }
    }

    private fun onItemSelected(task: Task){
        val detailsIntent = Intent(context, ContainerActivity::class.java).apply {
            putExtra(EXTRA_SCREEN_TYPE, ContainerActivity.SCREEN_TASK_DETAILS)
            putExtra(EXTRA_TASK_ID, task.id)
        }
        startActivity(detailsIntent)
    }

    private fun refreshTasks() {
        progress.gone()
        if (data.isNotEmpty()) {
            noData.invisible()
        } else {
            noData.visible()
        }
        adapter.setData(data as MutableList<Task>)
    }

    private fun addTask() {
        val dialog = AddTaskFragmentDialog.newInstance()
        dialog.setTaskAddedListener(this)
        dialog.show(childFragmentManager, dialog.tag)
    }

    override fun onTaskAdded(task: Task) {
        setData(repository.getAllTasks())
        refreshTasks()
    }

    override fun deleteAllTasks() {
        repository.deleteAllTasks()
        setData(repository.getAllTasks())
        refreshTasks()
    }

    override fun sortPriorityAscending() {
        setData(repository.getTasksPrioritySortedAscending())
        refreshTasks()
    }

    override fun sortPriorityDescending() {
        setData(repository.getTasksPrioritySortedDescending())
        refreshTasks()
    }

    private fun setData(newData: List<Task>){
        this.data = newData
    }

    companion object {
        fun newInstance(): TasksFragment {
            return TasksFragment()
        }
    }
}