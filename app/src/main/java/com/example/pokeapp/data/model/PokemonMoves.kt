package com.example.pokeapp.data.model

import com.google.gson.annotations.SerializedName

class PokemonMoves(
    @SerializedName("move")
    var move: PokemonMove
)