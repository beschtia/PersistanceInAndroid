package hr.etfos.davorlukic.taskie.ui.fragments.editTask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import hr.etfos.davorlukic.taskie.R
import hr.etfos.davorlukic.taskie.ui.interfaces.OnTaskEditedListener
import hr.etfos.davorlukic.taskie.model.Priority
import hr.etfos.davorlukic.taskie.model.Task
import hr.etfos.davorlukic.taskie.persistence.TasksRoomRepository
import kotlinx.android.synthetic.main.fragment_edit_priority.*
import kotlinx.android.synthetic.main.fragment_edit_priority.prioritySelector

class EditPriorityFragmentDialog: DialogFragment() {

    private var onTaskEditedListener: OnTaskEditedListener? = null
    private val repository = TasksRoomRepository()
    lateinit var task: Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FragmentDialogTheme)
    }

    fun setOnTaskEditedListener(listener: OnTaskEditedListener){
        onTaskEditedListener = listener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_priority, container)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initUi()
    }

    private fun initListeners() {
        saveEditPriorityAction.setOnClickListener {
            repository.editTaskPriority(task.id, prioritySelector.selectedItem as Priority)
            onTaskEditedListener?.onTaskEdited(task)
            dismiss()
        }
    }

    private fun initUi() {
        context?.let {
            prioritySelector.adapter = ArrayAdapter<Priority>(it, android.R.layout.simple_spinner_dropdown_item, Priority.values())
            when (task.priority) {
                Priority.LOW -> prioritySelector.setSelection(0)
                Priority.MEDIUM -> prioritySelector.setSelection(1)
                else -> prioritySelector.setSelection(2)
            }
        }
    }

    companion object{
        fun newInstance(): EditPriorityFragmentDialog {
            return EditPriorityFragmentDialog()
        }
    }
}