package com.example.pokeapp.data.model

import com.google.gson.annotations.SerializedName

data class PokemonAbility(
    @SerializedName("name")
    var name: String
)