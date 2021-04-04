package com.example.pokeapp.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pokeapp.R
import com.example.pokeapp.data.model.Pokemon
import kotlinx.android.synthetic.main.pokemon_list_item.view.*

/***
 * Adapter for customized Pokemon recycler view
 */

class PokemonRecyclerViewAdapter(private val listener:OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Pokemon> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PokemonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.pokemon_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PokemonViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(pokemonList: List<Pokemon>) {
        items = pokemonList
        notifyDataSetChanged()
    }

    inner class PokemonViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val pokemon_name_textView = itemView.pokemon_name_textView
        val pokemon_image_imageView = itemView.pokemon_image_imageView

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(pokemon: Pokemon) {
           val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(pokemon.image)
                .into(pokemon_image_imageView)
            pokemon_name_textView.text = pokemon.name
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if(position!=RecyclerView.NO_POSITION)
                listener.onItemClick(items[position].name)
        }

    }

    interface OnItemClickListener{
        fun onItemClick(pokemonName: String)
    }

}