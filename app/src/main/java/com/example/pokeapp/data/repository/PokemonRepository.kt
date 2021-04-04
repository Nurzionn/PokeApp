package com.example.pokeapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.pokeapp.data.model.Pokemon
import com.example.pokeapp.data.model.PokemonInfo
import com.example.pokeapp.data.model.PokemonResponse
import com.example.pokeapp.data.restful.PokemonService
import com.example.pokeapp.data.restful.WebHookService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


/***
 * Repository that makes the connection between the API and the View Model
 */

class PokemonRepository @Inject constructor(
    val pokemonService: PokemonService,
    val webHookService: WebHookService
) : IPokemonRepository {

    override fun getPokemons(): MutableLiveData<List<Pokemon>?> {
        val mutableLiveDataPokemonList = MutableLiveData<List<Pokemon>?>()
        lateinit var pokemonList: List<Pokemon>

        pokemonService.getPokemonsResponse().enqueue(object :
            Callback<PokemonResponse> {
            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                mutableLiveDataPokemonList.postValue(null)
            }

            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                val pokemonResponse: PokemonResponse? = response.body()
                if (pokemonResponse != null) {
                    pokemonList = pokemonResponse.results
                    for (i in 0..pokemonList.size - 1) {
                        pokemonList[i].image =
                            "https://pokeres.bastionbot.org/images/pokemon/${i + 1}.png"//"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${i+1}.png"
                        pokemonList[i].name = pokemonList[i].name.toUpperCase()
                    }
                    mutableLiveDataPokemonList.postValue(pokemonList)
                }
            }
        })
        return mutableLiveDataPokemonList
    }

    override fun getPokemonInfo(name: String): MutableLiveData<PokemonInfo?> {
        val mutableLiveDataPokemonInfo = MutableLiveData<PokemonInfo?>()
        lateinit var pokemonInfo: PokemonInfo

        pokemonService.getPokemonInfo(name).enqueue(object :
            Callback<PokemonInfo> {
            override fun onFailure(call: Call<PokemonInfo>, t: Throwable) {
                mutableLiveDataPokemonInfo.postValue(null)
            }

            override fun onResponse(
                call: Call<PokemonInfo>,
                responseByRange: Response<PokemonInfo>
            ) {
                if (responseByRange.body() != null) {
                    pokemonInfo = responseByRange.body()!!
                    pokemonInfo.imagem =
                        "https://pokeres.bastionbot.org/images/pokemon/${pokemonInfo.id}.png"//"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${i+1}.png"
                    mutableLiveDataPokemonInfo.postValue(pokemonInfo)
                }
            }
        })

        return mutableLiveDataPokemonInfo
    }

    override fun postPokemonInfo(json: String, pokemonName: String): MutableLiveData<ResponseBody?> {
        val mutableLiveDataResponse = MutableLiveData<ResponseBody?>()
        webHookService.addPokemon(json, pokemonName).enqueue(object:
            Callback<ResponseBody?> {
            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                mutableLiveDataResponse.postValue(null)
            }

            override fun onResponse(
                call: Call<ResponseBody?>,
                response: Response<ResponseBody?>
            ) {
                mutableLiveDataResponse.postValue(response.body())
            }
        })

        return mutableLiveDataResponse
    }


}