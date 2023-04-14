package com.example.adnroidmaster.superheroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.core.view.isVisible
import com.example.adnroidmaster.R
import com.example.adnroidmaster.databinding.ActivityDetailSuperheroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperheroActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_ID = "extra_id"    // Las constantes van en MAYUS por buenas prácticas
    }

    private lateinit var binding: ActivityDetailSuperheroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id:String = intent.getStringExtra(EXTRA_ID).orEmpty() // al llamar explícita la variable "id", Kotlin menciona que es nulable. Se puede quitar el "?" intercambiándolo por un ".orEmpty"
        getSuperheroInformation(id)
    }

    private fun getSuperheroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superheroDetail =
                getRetrofit().create(ApiService::class.java).getSuperheroDetail(id)
            
            if(superheroDetail.body() != null) {
                runOnUiThread { createUI(superheroDetail.body()!!) }   // con el "!!" le aseguramos que no es nulo, para que no truene
            }
        }
    }

    private fun createUI(superhero: SuperheroDetailResponse) {
        Picasso.get().load(superhero.image.url).into(binding.ivSuperhero)
        binding.tvSuperheroName.text = superhero.name
        prepareStats(superhero.powerstats)
        binding.tvSuperheroRealName.text = superhero.biography.fullName
        binding.tvSPublisher.text = superhero.biography.publisher
    }

    private fun prepareStats(powerstats: PowerStatsResponse) {
        updateHeight(binding.viewCombat, powerstats.combat)
        updateHeight(binding.viewDurability, powerstats.durability)
        updateHeight(binding.viewSpeed, powerstats.speed)
        updateHeight(binding.viewStrength, powerstats.strength)
        updateHeight(binding.viewIntelligence, powerstats.intelligence)
        updateHeight(binding.viewPower, powerstats.power)
    }

    private fun updateHeight(view: View, stat:String) {
        val params = view.layoutParams
        params.height = pxToDP(stat.toFloat())
        view.layoutParams = params
    }

    private fun pxToDP(px:Float):Int { // función para convertir pixeles en DP para el View
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt() // no se la sabe de memoria
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")  // SIEMPRE poner el slash al final. Error muy común omitirlo
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}