package com.example.pokeapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.pokeapp.data.model.Pokemon
import com.example.pokeapp.data.model.PokemonInfo
import okhttp3.ResponseBody

/***
 * Intermediate interface between the Repository and the API
 */

interface IPokemonRepository {
    fun getPokemons() : MutableLiveData<List<Pokemon>?>
    fun getPokemonInfo(name: String) : MutableLiveData<PokemonInfo?>
    fun postPokemonInfo(json: String, pokemonName: String) : MutableLiveData<ResponseBody?>
}