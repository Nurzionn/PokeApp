package com.example.pokeapp.di.module

import androidx.lifecycle.ViewModel
import com.example.pokeapp.di.annotation.ViewModelKey
import com.example.pokeapp.ui.main.SharedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/***
 * Module that provides a specific view model, in this case SharedViewModule type
 * The combined intomap and viewmodelkey annotations identify the desired class
 */

@Module
abstract class SharedViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SharedViewModel::class)
    abstract fun bindMainViewModel(viewModel: SharedViewModel): ViewModel
}