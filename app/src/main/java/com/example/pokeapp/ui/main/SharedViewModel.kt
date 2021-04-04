package com.example.pokeapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokeapp.data.model.Pokemon
import com.example.pokeapp.data.model.PokemonInfo
import com.example.pokeapp.data.repository.PokemonRepository
import okhttp3.ResponseBody
import javax.inject.Inject

/***
 * A shared view model used by two fragments (PokemonListFragment and PokemonDetailsFragment)
 * The injection of the constructor is to be able to use the repository provided
 */

class SharedViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) : ViewModel() {

    var pokemonLiveData = MutableLiveData<List<Pokemon>?>()
    var pokemonInfoLiveData = MutableLiveData<PokemonInfo?>()

    var pokemonName = ""
    lateinit var jsonBody:String

    fun changePokemonName(name: String){
        pokemonName = name
    }

    fun changeJsonBody(json: String){
        jsonBody = json
    }

    fun getPokemonsData() : MutableLiveData<List<Pokemon>?>{
        pokemonLiveData = pokemonRepository.getPokemons()
        return pokemonLiveData
    }

    fun getPokemonsInfoData() :  MutableLiveData<PokemonInfo?>{
        pokemonInfoLiveData = pokemonRepository.getPokemonInfo(pokemonName)
        return pokemonInfoLiveData
    }

    fun postPokemonInfo() : MutableLiveData<ResponseBody?>{
        return pokemonRepository.postPokemonInfo(jsonBody, pokemonName)
    }
}
