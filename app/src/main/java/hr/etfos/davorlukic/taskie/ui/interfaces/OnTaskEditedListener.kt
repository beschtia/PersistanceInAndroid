package hr.etfos.davorlukic.taskie.ui.interfaces

import hr.etfos.davorlukic.taskie.model.Task

interface OnTaskEditedListener {
    fun onTaskEdited(task: Task)
}