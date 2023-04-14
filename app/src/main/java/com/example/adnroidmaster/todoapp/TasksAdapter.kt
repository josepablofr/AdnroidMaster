package com.example.adnroidmaster.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adnroidmaster.R

class TasksAdapter(var tasks: List<Task>, private val onTaskSelected: (Int) -> Unit) :
    RecyclerView.Adapter<TasksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo_task, parent, false)
        return TasksViewHolder(view)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.render(tasks[position])
        holder.itemView.setOnClickListener { onTaskSelected(position) }  // Se ejecuta la función lambda
    }

    override fun getItemCount() = tasks.size
}