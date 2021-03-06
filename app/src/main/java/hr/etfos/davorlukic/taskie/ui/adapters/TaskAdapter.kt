package hr.etfos.davorlukic.taskie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import hr.etfos.davorlukic.taskie.R
import hr.etfos.davorlukic.taskie.model.Task

class TaskAdapter(private val onItemSelected: (Task) -> Unit) : Adapter<TaskHolder>() {

    private val data: MutableList<Task> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskHolder(v)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bindData(data[position], onItemSelected)
    }

    fun setData(data: MutableList<Task>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun removeAt(position: Int){
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getTaskID(position: Int) = data[position].id
}





