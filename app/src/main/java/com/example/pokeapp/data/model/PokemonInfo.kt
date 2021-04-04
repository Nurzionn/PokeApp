package com.example.pokeapp.data.model

import com.google.gson.annotations.SerializedName

class PokemonInfo(
    var imagem: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("weight")
    var weight: Int,
    @SerializedName("height")
    var height: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("base_experience")
    var base_experience: Int,
    @SerializedName("abilities")
    var abilities: List<PokemonAbilities>,
    @SerializedName("types")
    var types: List<PokemonTypes>,
    @SerializedName("moves")
    var moves: List<PokemonMoves>
)