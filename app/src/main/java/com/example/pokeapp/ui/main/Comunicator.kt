package com.example.pokeapp.ui.main

/***
 * Interface for communication between two fragments belonging to the same activity
 * The communication includes data exchange
 */

interface Comunicator {
    fun passData(pokemonName:String)
    fun goBack()
}