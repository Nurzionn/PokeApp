package com.example.pokeapp.data.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    var image: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("url")
    var url: String
)