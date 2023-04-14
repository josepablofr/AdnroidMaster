package com.example.adnroidmaster.superheroapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adnroidmaster.R

class SuperheroAdapter(
    var superheroList: List<SuperheroItemResponse> = emptyList(),
    private val onItemSelected: (String) -> Unit,   // Con esto pasamos el ID para poder acceder al detalle de los superheroes
) : // Inicializamos con una lista vacía
    RecyclerView.Adapter<SuperheroViewHolder>() {

    fun updateList(list: List<SuperheroItemResponse>) {
        superheroList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SuperheroViewHolder {  // En el medio fuimos a crear un Layout Resource llamdo "item_superhero"
        return SuperheroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false))
    }

    override fun onBindViewHolder(
        viewholder: SuperheroViewHolder,
        position: Int,
    ) {  // al implementar los métodos venía un nombre del parámetro pero lo podemos cambiar
        viewholder.bind(superheroList[position],onItemSelected)
    }

    override fun getItemCount() = superheroList.size
}