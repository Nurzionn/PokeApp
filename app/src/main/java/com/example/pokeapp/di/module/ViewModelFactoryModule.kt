package com.example.pokeapp

import androidx.lifecycle.ViewModelProvider
import com.example.pokeapp.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

/***
 * Module for view models factory, that provides any type of view model
 */

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory
}