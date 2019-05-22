package hr.etfos.davorlukic.taskie.ui.fragments.editTask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import hr.etfos.davorlukic.taskie.R
import hr.etfos.davorlukic.taskie.ui.interfaces.OnTaskEditedListener
import hr.etfos.davorlukic.taskie.model.Task
import hr.etfos.davorlukic.taskie.persistence.TasksRoomRepository
import kotlinx.android.synthetic.main.fragment_edit_title.*

class EditTitleFragmentDialog: DialogFragment() {

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
        return inflater.inflate(R.layout.fragment_edit_title, container)
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
        saveEditAction.setOnClickListener {
            repository.editTaskTitle(task.id, editTaskInput.text.toString())
            onTaskEditedListener?.onTaskEdited(task)
            dismiss()
        }
    }

    private fun initUi() {
        editTaskHeading.text = getString(R.string.editTaskTitleHeadingText)
        editTaskInput.setText(task.title)
    }

    companion object{
        fun newInstance(): EditTitleFragmentDialog {
            return EditTitleFragmentDialog()
        }
    }
}