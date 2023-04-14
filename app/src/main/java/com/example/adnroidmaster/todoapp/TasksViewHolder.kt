package com.example.adnroidmaster.todoapp

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.adnroidmaster.R

class TasksViewHolder (view: View) : RecyclerView.ViewHolder(view)  {

    private val tvTask:TextView = view.findViewById(R.id.tvTask)
    private val cbTask:CheckBox = view.findViewById(R.id.cbTask)

    fun render(task: Task) {

        if(task.isSelected){
            tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG   // En Stackoverflow, Aristi encontró q con esto se tacha el texto :D
        } else
            tvTask.paintFlags = tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()   // inv de "invertido"

        tvTask.text = task.name
        cbTask.isChecked = task.isSelected

        val color = when(task.category) {      // al poner when, luego click izq e "implementar variables" ya que está llamando a una sealed class y tiene que ser extensiva
            TaskCategory.Business -> R.color.todo_business_category
            TaskCategory.Personal -> R.color.todo_personal_category
            TaskCategory.Other -> R.color.todo_other_category
        }

        cbTask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(cbTask.context, color)
        //cuidado con el contexto en los Reciclers Views. Va aquí y no en la línea de "fun(render) (línea 17)"
        )
    }
}



// Nota: todas las vistas tienen el Contexto de la Actividad!