package com.example.pokeapp.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.R
import com.example.pokeapp.data.model.PokemonMove
import com.example.pokeapp.data.model.PokemonMoves
import kotlinx.android.synthetic.main.move_list_item.view.*

/***
 * Adapter for recycler view that show the moves from a choosen Pokemon
 */

class PokemonMovesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<PokemonMoves> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MoveViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.move_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MoveViewHolder -> {
                holder.bind(items[position].move)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(moves: List<PokemonMoves>) {
        items = moves
        notifyDataSetChanged()
    }

    inner class MoveViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val move_name = view.move_name_textView

        fun bind(pokemonMove: PokemonMove) {
            move_name.text = pokemonMove.name
        }
    }

}