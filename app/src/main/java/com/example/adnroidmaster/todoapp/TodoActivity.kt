package com.example.adnroidmaster.todoapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adnroidmaster.R
import com.example.adnroidmaster.todoapp.TaskCategory.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoActivity : AppCompatActivity() {

    private val categories = listOf(
        Business,
        Personal,
        Other
    )

    private val tasks = mutableListOf(
        Task("pruebaBusiness", Business),
        Task("pruebaPersonal", Personal),
        Task("pruebaOther", Other)
    )

    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var rvTasks: RecyclerView
    private lateinit var tasksAdapter: TasksAdapter

    private lateinit var fabAddTask: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        initComponent()
        initUI()
        initListeners()
    }

    private fun initListeners() {
        fabAddTask.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)

        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddTask.setOnClickListener {
            val currentTask = etTask.text.toString()
            if(currentTask.isNotEmpty()) {


                val selectedID = rgCategories.checkedRadioButtonId
                val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedID)
                val currentCategory: TaskCategory = when(selectedRadioButton.text) {
                    getString(R.string.todo_dialog_category_business) -> Business
                    getString(R.string.todo_dialog_category_personal) -> Personal
                    else -> Other
                }

                tasks.add(Task(etTask.text.toString(), currentCategory))
                updateTasks()
                dialog.hide()
            }
        }


        dialog.show()
    }

    //Ojo con los Reciclers Views. Sólo en Composable se actualizan "live", por método tradicional se tiene que
    // actualizar la lista inicial de tareas ejecutando una función

    private fun initComponent() {
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)
        fabAddTask = findViewById(R.id.fabAddTask)
    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories) {position -> updateCategories(position)}
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        rvCategories.adapter = categoriesAdapter

        tasksAdapter = TasksAdapter(tasks) {position -> onItemSelected(position)}  // Otra forma de hacerlo: tasksAdapter = TasksAdapter(tasks, {onItemSelected(it)})
        rvTasks.layoutManager = LinearLayoutManager(this)   // A diferencia del layoutMgr de arriba, no se especifica Vertical ya que Vertical es por default, sólo se pone "this"
        rvTasks.adapter = tasksAdapter
    }

    private fun onItemSelected(position: Int) {
        tasks[position].isSelected = !tasks[position].isSelected
        updateTasks()
    }

    private fun updateCategories(position: Int) {
        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTasks()
    }

    private fun updateTasks(){
        val selectedCategories: List<TaskCategory> = categories.filter { it.isSelected }
        val newTasks = tasks.filter { selectedCategories.contains(it.category) }
        tasksAdapter.tasks = newTasks
        tasksAdapter.notifyDataSetChanged() // parte de la fun xa avisar al Adaptador de nuevas Tareas
    }
}

// cada Adapter es para 1 RecyclerView