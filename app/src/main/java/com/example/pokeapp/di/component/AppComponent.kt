package com.example.pokeapp.di

import android.content.Context
import com.example.pokeapp.MainComponent
import com.example.pokeapp.NetworkModule
import com.example.pokeapp.ViewModelFactoryModule
import com.example.pokeapp.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

/***
 * Parent component
 */

@Singleton
@Component(modules = [ViewModelFactoryModule::class, SubcomponentsModule::class, NetworkModule::class, AppModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    fun mainComponent(): MainComponent.Factory

}

@Module(subcomponents = [MainComponent::class])
object SubcomponentsModule