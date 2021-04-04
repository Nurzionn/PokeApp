package com.example.pokeapp.ui.main

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokeapp.MainActivity
import com.example.pokeapp.R
import com.example.pokeapp.recyclerview.ItemDecoration
import com.example.pokeapp.recyclerview.adapter.PokemonRecyclerViewAdapter
import kotlinx.android.synthetic.main.pokemon_details_fragment.*
import kotlinx.android.synthetic.main.pokemon_list_fragment.*
import javax.inject.Inject

/***
 * The first fragment that show the initial list of 150 pokemon
 */

class PokemonListFragment : Fragment(), PokemonRecyclerViewAdapter.OnItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<SharedViewModel> { viewModelFactory }

    private lateinit var pokemonRecyclerViewAdapter: PokemonRecyclerViewAdapter
    private lateinit var comunicator: Comunicator

    companion object {
        fun newInstance() = PokemonListFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity() as MainActivity).mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.pokemon_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        comunicator = activity as Comunicator
        (activity as AppCompatActivity).setSupportActionBar(initial_toolbar)
        setupUIComponents()
        getPokemons()
    }

    private fun setupUIComponents() {
        pokemon_recycler_view.apply {
            itemAnimator = DefaultItemAnimator()
            layoutManager = GridLayoutManager(activity, 2)
            val itemDecoration = ItemDecoration(15)
            addItemDecoration(itemDecoration)
            pokemonRecyclerViewAdapter = PokemonRecyclerViewAdapter(this@PokemonListFragment)
            adapter = pokemonRecyclerViewAdapter
        }


        pokemon_progressBar.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                indeterminateTintList = ColorStateList.valueOf(Color.RED)
        }
    }

    private fun getPokemons() {
        viewModel.getPokemonsData().observe(viewLifecycleOwner, Observer {
            if (it == null)
                Toast.makeText(
                    requireActivity().applicationContext,
                    "Error in get data from internet!",
                    Toast.LENGTH_LONG
                ).show()
            else
                it.let(pokemonRecyclerViewAdapter::submitList)
            pokemon_progressBar.visibility = View.INVISIBLE
        })
    }

    override fun onItemClick(pokemonName: String) {
        comunicator.passData(pokemonName.toLowerCase())
    }

}
