package com.example.adnroidmaster.superheroapp

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(
    @SerializedName("response") val response:String, // "response" viene de que así aparece la 1a línea en el JSON de SuperHero cuando llamas a la API.
    @SerializedName("results") val superheroes:List<SuperheroItemResponse>
)

data class SuperheroItemResponse(
    @SerializedName("id") val superheroId: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val superheroImage: SuperheroImageResponse
)

data class SuperheroImageResponse(@SerializedName("url") val url: String) // creamos otra data class al estar este item dentro de otro item en el API


// La estructura de los datos la vemos: nos metemos a esta página https://www.superheroapi.com/api.php/10159690569607252/search/super
// luego copiamos la URL en un Json Formater como: https://jsoneditoronline.org/, pegamos la URL y vemos la estructura

// Según como podemos ver en la página, la estructura de datos del Json es: 1 "response" y luego una lista de atributos. Esta lista se declara separada en una "Data Class"
