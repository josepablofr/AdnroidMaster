package com.example.adnroidmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.adnroidmaster.firstapp.FirstAppActivity
import com.example.adnroidmaster.imccalculator.ImcCalculatorActivity
import com.example.adnroidmaster.superheroapp.SuperHeroListActivity
import com.example.adnroidmaster.todoapp.TodoActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val btnSaludApp = findViewById<Button>(R.id.btnSaludApp)
        val btnImcApp = findViewById<Button>(R.id.btnIMCApp)
        val btnTODO = findViewById<Button>(R.id.btnTODO)
        val btnSuperhero = findViewById<Button>(R.id.btnSuperhero)

        btnSaludApp.setOnClickListener { navigateToSaludApp() }
        btnImcApp.setOnClickListener { navigateToImcApp() }
        btnTODO.setOnClickListener { navigateToTodoApp() }
        btnSuperhero.setOnClickListener { navigateToSuperheroApp() }
    }

    private fun navigateToTodoApp() {
        val intent = Intent(this, TodoActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToImcApp() {
        val intent = Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSaludApp(){
        val intent = Intent(this, FirstAppActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSuperheroApp() {
        val intent = Intent(this, SuperHeroListActivity::class.java)
        startActivity(intent)
    }

}