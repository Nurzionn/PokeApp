package com.example.pokeapp.data.model

import com.google.gson.annotations.SerializedName

class PokemonTypes(
    @SerializedName("type")
    var pokemonType: PokemonType
)