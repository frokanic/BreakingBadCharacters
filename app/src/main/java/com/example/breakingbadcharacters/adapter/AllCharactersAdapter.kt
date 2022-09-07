package com.example.breakingbadcharacters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breakingbadcharacters.databinding.AllCharactersListItemBinding
import com.example.breakingbadcharacters.remote.response.CharactersItem

class AllCharactersAdapter: RecyclerView.Adapter<AllCharactersAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(val binding: AllCharactersListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object  : DiffUtil.ItemCallback<CharactersItem>() {
        override fun areItemsTheSame( oldItem: CharactersItem, newItem: CharactersItem): Boolean {
            return oldItem.char_id == newItem.char_id
        }

        override fun areContentsTheSame(oldItem: CharactersItem, newItem: CharactersItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            AllCharactersListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val binding = holder.binding
        val character = differ.currentList[position]

        holder.itemView.apply {
            Glide.with(this).load(character.img).into(binding.imgCharactersRecyclerViewImage)
            binding.tvCharactersRecyclerViewTitle.text = character.name
            setOnClickListener {
                onItemClickListener?.let { it(character) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((CharactersItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (CharactersItem) -> Unit) {
        onItemClickListener = listener
    }


}
