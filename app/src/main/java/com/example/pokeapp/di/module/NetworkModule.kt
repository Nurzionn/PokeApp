package com.example.pokeapp

import com.example.pokeapp.data.restful.PokemonService
import com.example.pokeapp.data.restful.WebHookService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/***
 * Module that provides the API Service and the Retrofit Client
 */

@Module
class NetworkModule {
    
    val POKEMON_SERVER = "https://pokeapi.co/api/v2/"
    val WEBHOOK_SERVER = "https://webhook.site/"

    @Provides
    @Singleton
    @Named("retrofit_1")
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(POKEMON_SERVER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("retrofit_2")
    fun provideRetrofit2(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(WEBHOOK_SERVER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiClient(@Named("retrofit_1") retrofit : Retrofit) : PokemonService {
        return retrofit.create(PokemonService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiClient2(@Named("retrofit_2") retrofit : Retrofit) : WebHookService {
        return retrofit.create(WebHookService::class.java)
    }
}