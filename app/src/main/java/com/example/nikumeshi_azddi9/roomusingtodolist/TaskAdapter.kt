package com.example.nikumeshi_azddi9.roomusingtodolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_list_row.view.*

class TaskAdapter(context: Context,
                  private val tasks:List<Task>,
                  private val onItemClicked: (Task)->Unit) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = inflater.inflate(R.layout.task_list_row, parent, false)
        val vh = TaskViewHolder(view)

        view.setOnClickListener{
            val position = vh.adapterPosition
            val task = tasks[position]
            onItemClicked(task)
        }
        return vh
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.priority.text = PriorityEnum.getLabel(tasks[position].priority)
        holder.title.text = tasks[position].title
        holder.date.text = tasks[position].date
        holder.time.text = tasks[position].time
    }

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val priority: TextView = view.adap_priority
        val title: TextView = view.adap_title
        val date: TextView = view.adap_date
        val time: TextView = view.adap_time
    }

}