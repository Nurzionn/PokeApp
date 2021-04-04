package com.example.pokeapp

import com.example.pokeapp.di.module.SharedViewModelModule
import com.example.pokeapp.ui.main.PokemonDetailsFragment
import com.example.pokeapp.ui.main.PokemonListFragment
import dagger.Subcomponent

/***
 * Subcomponent that inherits from the parent component and injects the general (and specifics) dependencies into specific classes
 */

@Subcomponent(modules = [SharedViewModelModule::class])
interface MainComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create(): MainComponent
    }

    fun inject(activity: PokemonListFragment)
    fun inject(activity: PokemonDetailsFragment)
}