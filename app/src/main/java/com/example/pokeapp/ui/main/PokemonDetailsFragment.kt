package com.example.pokeapp.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.pokeapp.MainActivity
import com.example.pokeapp.R
import com.example.pokeapp.data.model.PokemonInfo
import com.example.pokeapp.data.model.post.PokemonInfoPost
import com.example.pokeapp.recyclerview.ItemDecoration
import com.example.pokeapp.recyclerview.adapter.PokemonMovesAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.pokemon_details_fragment.*
import javax.inject.Inject

/***
 * The second fragment which shows the details of the chosen pokemon
 */

class PokemonDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<SharedViewModel> { viewModelFactory }

    lateinit var adapterMoves: PokemonMovesAdapter
    lateinit var pokemonInfo: PokemonInfo
    lateinit var pokemonInfoPost: PokemonInfoPost
    lateinit var pokemonName: String
    var pokemonAdded = false

    private lateinit var comunicator: Comunicator

    companion object {
        fun newInstance() = PokemonDetailsFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity() as MainActivity).mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pokemon_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
        comunicator = activity as Comunicator
        pokemonName = arguments?.getString("Pokemon Name") ?: ""
        setupUIComponents()
        getPokemonInfo()
    }

    private fun setupUIComponents() {
        pokemon_moves_recyclerView.apply {
            itemAnimator = DefaultItemAnimator()
            layoutManager = GridLayoutManager(activity, 2)
            val itemDecoration = ItemDecoration(5)
            addItemDecoration(itemDecoration)
            adapterMoves = PokemonMovesAdapter()
            adapter = adapterMoves
        }

        favorite_checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!pokemonAdded) {

                viewModel.postPokemonInfo().observe(viewLifecycleOwner, Observer {
                    if(it==null)
                        Toast.makeText(
                            requireActivity().applicationContext,
                            "Error adding Pokemon to favorites!",
                            Toast.LENGTH_LONG
                        ).show()
                    else{
                        Toast.makeText(
                            requireActivity().applicationContext,
                            "Pokemon has been successfully added to favorites!",
                            Toast.LENGTH_LONG
                        ).show()

                        pokemonAdded = true
                    }
                })
            } else
                Toast.makeText(
                    requireActivity().applicationContext,
                    "Pokémon has already been added to favorites and posted on internet!",
                    Toast.LENGTH_LONG
                ).show()
        }

        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    private fun getPokemonInfo() {
        viewModel.changePokemonName(pokemonName)

        viewModel.getPokemonsInfoData().observe(viewLifecycleOwner, Observer {
            if (it == null)
                Toast.makeText(
                    requireActivity().applicationContext,
                    "Error in get data from internet!",
                    Toast.LENGTH_LONG
                ).show()
            else {
                pokemonInfo = it
                fillComponents()
            }
        })
    }

    fun fillComponents() {
        adapterMoves.submitList(pokemonInfo.moves)//pokemonInfo.moves.let { adapterMoves::submitList }
        when (pokemonInfo.id) {
            in 1..9 -> pokemon_id_textView.text = "#00${pokemonInfo.id}"
            in 10..99 -> pokemon_id_textView.text = "#0${pokemonInfo.id}"
            else -> pokemon_id_textView.text = "#${pokemonInfo.id}"
        }
        pokemon_name_textView.text = "${pokemonInfo.name.toUpperCase()}"
        height_textView.text = "${pokemonInfo.height} M"
        weight_textView.text = "${pokemonInfo.weight} KG"
        basexp_textView.text = "${pokemonInfo.base_experience}"
        pokemon_type_textView.text = "${pokemonInfo.types[0].pokemonType.name}"
        Glide.with(this)
            .load(pokemonInfo.imagem)
            .into(pokemon_image_imageView)
        createJSonObject()
        changeLinearLayoutColor()
    }

    fun createJSonObject() {
        pokemonInfoPost = PokemonInfoPost(
            pokemonInfo.imagem,
            pokemonInfo.name,
            pokemonInfo.height,
            pokemonInfo.weight,
            pokemonInfo.id,
            pokemonInfo.base_experience,
            pokemonInfo.abilities,
            pokemonInfo.types,
            pokemonInfo.moves
        )

        val gson = Gson()
        viewModel.changeJsonBody(gson.toJson(pokemonInfoPost))
    }

    fun changeLinearLayoutColor() {
        var colorType = R.color.colorPrimary
        when (pokemonInfo.types[0].pokemonType.name) {
            "normal" -> colorType = R.color.normal
            "fire" -> colorType = R.color.fire
            "water" -> colorType = R.color.water
            "grass" -> colorType = R.color.grass
            "electric" -> colorType = R.color.electric
            "ice" -> colorType = R.color.ice
            "fighting" -> colorType = R.color.fighting
            "poison" -> colorType = R.color.poison
            "ground" -> colorType = R.color.ground
            "flying" -> colorType = R.color.flying
            "psychic" -> colorType = R.color.psychic
            "bug" -> colorType = R.color.bug
            "rock" -> colorType = R.color.rock
            "ghost" -> colorType = R.color.ghost
            "dark" -> colorType = R.color.dark
            "dragon" -> colorType = R.color.dragon
            "steel" -> colorType = R.color.steel
            "fairy" -> colorType = R.color.fairy
        }

        context?.let { ContextCompat.getColor(it, colorType) }?.let {
            principal_linearLayout.setBackgroundColor(it)
            toolbar.setBackgroundColor(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> comunicator.goBack()
        }
        return super.onOptionsItemSelected(item)
    }


}
