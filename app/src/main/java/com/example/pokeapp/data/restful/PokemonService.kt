package com.example.pokeapp.data.restful

import com.example.pokeapp.data.model.PokemonInfo
import com.example.pokeapp.data.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/***
 * interface that provides the Pokemon server data through a REST API
 */

interface PokemonService {

    @GET("pokemon?limit=100")
    fun getPokemonsResponse() : Call<PokemonResponse>

    @GET("pokemon/{name}")
    fun getPokemonInfo(@Path("name") name: String) : Call<PokemonInfo>
}