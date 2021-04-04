package com.example.pokeapp.di.module

import android.app.Application
import android.content.Context
import com.example.pokeapp.data.repository.PokemonRepository
import com.example.pokeapp.data.restful.PokemonService
import com.example.pokeapp.data.restful.WebHookService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/***
 * Module that provides the applicatyion context and the repository used to get the data from internet
 */


@Module
class AppModule() {

    @Provides
    @Singleton
    fun provideContext (application: Application) : Context {
        return application
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(pokemonService: PokemonService, webHookService: WebHookService) : PokemonRepository {
        return PokemonRepository(pokemonService, webHookService)
    }
}