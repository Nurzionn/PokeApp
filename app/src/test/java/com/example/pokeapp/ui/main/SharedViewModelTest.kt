package com.example.pokeapp.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.pokeapp.data.model.Pokemon
import com.example.pokeapp.data.model.PokemonInfo
import com.example.pokeapp.data.repository.PokemonRepository
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class SharedViewModelTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private val pokemonRepository = mockk<PokemonRepository>(relaxed = true)

    @MockK
    private val pokemonListObserver = mockk<Observer<List<Pokemon>?>>(relaxed = true)

    @MockK
    private val pokemonInfoObserver = mockk<Observer<PokemonInfo?>>(relaxed = true)

    @Test
    fun `when view model fetches Pokemons list with success then set Pokemons mutableLiveData`() {
        val viewModel = instantiateViewModel()
        viewModel.pokemonLiveData.observeForever(pokemonListObserver)
        val mockedList = MutableLiveData<List<Pokemon>?>(listOf<Pokemon>(Pokemon("Test", "Test", "Test")))

        every { pokemonRepository.getPokemons()} returns mockedList

        viewModel.getPokemonsData()

        verify { pokemonRepository.getPokemons() }
        verify { pokemonListObserver.onChanged(mockedList.value) }
    }

    @Test
    fun `when view model fetches Pokemon data with success then set PokemonInfo mutableLiveData`() {
        val viewModel = instantiateViewModel()
        viewModel.getPokemonsInfoData().observeForever(pokemonInfoObserver)
        val mockedPokemonInfo = MutableLiveData<PokemonInfo?>(PokemonInfo("Test", "Test", 1, 1, 1, 1, listOf(), listOf(), listOf()))

        every { pokemonRepository.getPokemonInfo("") }returns mockedPokemonInfo

        viewModel.getPokemonsInfoData()

        verify{(pokemonRepository).getPokemonInfo("") }
        verify{(pokemonInfoObserver).onChanged(mockedPokemonInfo.value)}
    }

    private fun instantiateViewModel(): SharedViewModel {
        return SharedViewModel(pokemonRepository)
    }
}
