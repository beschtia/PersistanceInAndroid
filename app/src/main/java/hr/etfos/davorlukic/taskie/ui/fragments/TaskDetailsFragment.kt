package hr.etfos.davorlukic.taskie.ui.fragments

import android.os.Bundle
import android.view.View
import hr.etfos.davorlukic.taskie.R
import hr.etfos.davorlukic.taskie.common.EXTRA_TASK_ID
import hr.etfos.davorlukic.taskie.common.displayToast
import hr.etfos.davorlukic.taskie.model.Task
import hr.etfos.davorlukic.taskie.persistence.TasksRoomRepository
import hr.etfos.davorlukic.taskie.ui.fragments.base.BaseFragment
import hr.etfos.davorlukic.taskie.ui.fragments.editTask.EditDescriptionFragmentDialog
import hr.etfos.davorlukic.taskie.ui.fragments.editTask.EditPriorityFragmentDialog
import hr.etfos.davorlukic.taskie.ui.fragments.editTask.EditTitleFragmentDialog
import hr.etfos.davorlukic.taskie.ui.interfaces.OnTaskEditedListener
import kotlinx.android.synthetic.main.fragment_task_details.*

class TaskDetailsFragment : BaseFragment(), OnTaskEditedListener {

    private val repository = TasksRoomRepository()
    private var taskID = NO_TASK

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_task_details
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(EXTRA_TASK_ID)?.let { taskID = it }
        tryDisplayTask(taskID)
    }

    private fun tryDisplayTask(id: Int) {
        try {
            val task = repository.get(id)
            displayTask(task)
        } catch (e: NoSuchElementException) {
            context?.displayToast(getString(R.string.noTaskFound))
        }
    }

    private fun displayTask(task: Task) {
        detailsTaskTitle.text = task.title
        detailsTaskTitle.setOnClickListener{onTitleClick(task)}

        detailsTaskDescription.text = task.description
        detailsTaskDescription.setOnClickListener { onDescriptionClick(task) }

        detailsPriorityView.setBackgroundResource(task.priority.getColor())
        detailsPriorityView.setOnClickListener { onPriorityClick(task) }
    }

    private fun onPriorityClick(task: Task) {
        val dialog = EditPriorityFragmentDialog.newInstance()
        dialog.task = task
        dialog.setOnTaskEditedListener(this)
        dialog.show(childFragmentManager, dialog.tag)
    }

    private fun onDescriptionClick(task: Task) {
        val dialog = EditDescriptionFragmentDialog.newInstance()
        dialog.task = task
        dialog.setOnTaskEditedListener(this)
        dialog.show(childFragmentManager,dialog.tag)
    }

    override fun onTaskEdited(task: Task) {
        tryDisplayTask(task.id)
    }

    private fun onTitleClick(task: Task) {
        val dialog = EditTitleFragmentDialog.newInstance()
        dialog.task=task
        dialog.setOnTaskEditedListener(this)
        dialog.show(childFragmentManager,dialog.tag)
    }

    companion object {

        const val NO_TASK = -1
        fun newInstance(taskId: Int): TaskDetailsFragment {
            val bundle = Bundle().apply { putInt(EXTRA_TASK_ID, taskId) }
            return TaskDetailsFragment().apply { arguments = bundle }
        }
    }
}
