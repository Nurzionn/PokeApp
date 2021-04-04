package com.example.pokeapp.data.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("results")
    var results: List<Pokemon>
)