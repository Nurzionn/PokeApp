package com.example.pokeapp

import android.app.Application
import com.example.pokeapp.di.ApplicationComponent
import com.example.pokeapp.di.DaggerApplicationComponent

/***
 * The class that creates and provides Dagger functionalities for the application, at the start
 */

class PokemonAPP : Application() {
    lateinit var appComponent : ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.factory().create(this)
    }
}