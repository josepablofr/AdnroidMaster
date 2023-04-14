package com.example.adnroidmaster.superheroapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/api/10159690569607252/search/{name}")
    suspend fun getSuperheroes(@Path("name") superheroName:String): Response<SuperHeroDataResponse>

    @GET("/api/10159690569607252/{id}")
    suspend fun getSuperheroDetail(@Path("id") superheroId:String): Response<SuperheroDetailResponse>

}


// Para trabajar con Corrutinas ... Cuando vamos a usar corrutinas en una función, la función debe empezar con "suspend fun"