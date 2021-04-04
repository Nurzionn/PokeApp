package com.example.pokeapp.data.model

import com.google.gson.annotations.SerializedName

data class PokemonAbilities(
    @SerializedName("ability")
    var abitlity: PokemonAbility
)