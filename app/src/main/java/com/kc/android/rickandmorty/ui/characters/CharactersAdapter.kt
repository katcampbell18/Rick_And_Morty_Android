package com.kc.android.rickandmorty.ui.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.kc.android.rickandmorty.R
import com.kc.android.rickandmorty.data.entities.CharacterItem
import com.kc.android.rickandmorty.databinding.ItemLayoutBinding


class CharactersAdapter(private val listener: CharacterItemListener) : RecyclerView.Adapter<CharactersViewHolder>() {

    interface CharacterItemListener {
        fun onClickedCharacter(characterId: Int)
    }

    private val items = ArrayList<CharacterItem>()

    fun setItems(items: ArrayList<CharacterItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val binding: ItemLayoutBinding = ItemLayoutBinding.inflate(LayoutInflater.from
            (parent.context), parent, false)
        return CharactersViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

class CharactersViewHolder(private val itemBinding: ItemLayoutBinding,
                           private val listener: CharactersAdapter.CharacterItemListener
                           ) : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

    private lateinit var character: CharacterItem

    init{
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: CharacterItem) {
        this.character = item
        itemBinding.nameTextView.text = item.name
        itemBinding.speciesAndStatusTextView.text =
            itemView.context.getString(R.string.character_species_and_status, item.species, item.status)
        Glide.with(itemBinding.root)
            .load(item.image)
            .transform(CircleCrop())
            .into(itemBinding.characterImageView)
    }

    override fun onClick(view: View?) {
        listener.onClickedCharacter(character.id)
    }
}
