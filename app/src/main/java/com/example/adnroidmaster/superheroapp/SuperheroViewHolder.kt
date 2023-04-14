package com.example.adnroidmaster.superheroapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.adnroidmaster.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperheroViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superheroItemResponse: SuperheroItemResponse, onItemSelected: (String) -> Unit){
        binding.tvSuperheroName.text = superheroItemResponse.name
        // para llamar las imágenes de los superheroes que asu vez están en una URL, agregamos una librería llamada Picasso (Glide tmb es buena)
        Picasso.get().load(superheroItemResponse.superheroImage.url).into(binding.ivSuperhero)// en la página de Picasso viene la documentación
        binding.root.setOnClickListener { onItemSelected(superheroItemResponse.superheroId) }
    }
}