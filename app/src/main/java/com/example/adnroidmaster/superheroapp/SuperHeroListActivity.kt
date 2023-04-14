package com.example.adnroidmaster.superheroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adnroidmaster.R
import com.example.adnroidmaster.databinding.ActivitySuperHeroListBinding
import com.example.adnroidmaster.superheroapp.DetailSuperheroActivity.Companion.EXTRA_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuperHeroListBinding  // Esto es el ViewBinding
    private lateinit var retrofit: Retrofit

    private lateinit var adapter: SuperheroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener   // le picamos encima de "object" e implementamos los 2 métodos q nos pide
        {
            override fun onQueryTextSubmit(query: String?): Boolean {    // método para cuando picamos la lupita del teclado para buscar
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })

        adapter = SuperheroAdapter{navigateToDetail(it)} // con función lambda, mandamos el id de los superheroes para acceder al detalle
        binding.rvSuperHero.setHasFixedSize(true)
        binding.rvSuperHero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperHero.adapter = adapter
    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
        // "Main" para hilo principal. IO" para hilo secundario. Entonces lo que pongamos dentro del ".launch" va a correr en un hilo secundario.
        // Dentro de la Corroutina, no se deben llamar a cambios en la interfaz de UI o truena
            val myResponse: Response<SuperHeroDataResponse> = retrofit.create(ApiService::class.java).getSuperheroes(query)
            if(myResponse.isSuccessful){
                Log.i("peperabasa","funciona :)")
                val response: SuperHeroDataResponse? = myResponse.body()
                if(response != null){
                    Log.i("peperabasa", response.toString())
                    runOnUiThread {   // para que corra en el hilo ppal, pues estamos aún dentro de una Corrutina
                        adapter.updateList(response.superheroes)
                        binding.progressBar.isVisible = false
                    }
                }
            } else {
                Log.i("peperabasa","no funciona :(")
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")  // SIEMPRE poner el slash al final. Error muy común omitirlo
            .addConverterFactory(GsonConverterFactory.create())
        .build()
    }

    private fun navigateToDetail(id:String){   // Esta función para navegar a la pantalla de detalle la metemos en una lambda en el SuperheroAdapter
        val intent = Intent(this, DetailSuperheroActivity::class.java)
        intent.putExtra(EXTRA_ID,id)  // la clave EXTRA_ID la metemos en una constante declarada en un companion object en DetailSuperheroActivity
        startActivity(intent)
    }
}


// El ViewBinding es la nueva forma de engancharnos (acceder) a las vistas (en lugar del findViewbyID). En el Graddle APP se agregan las sigs líneas:
//viewBinding {
//    enable = true
//}
//
// Pero como no me jaló, usé la otra forma:
//   buildFeatures{
//        viewBinding = true
//    }