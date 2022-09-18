package com.example.wgctestandroidapp.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wgctestandroidapp.databinding.ItemTitreAlbumsListBinding
import com.example.wgctestandroidapp.domain.Album

class AlbumListAdapter :
    ListAdapter<Album, AlbumListAdapter.AlbumViewHolder>(Companion) {


    private var context: Context? = null


    inner class AlbumViewHolder(val binding: ItemTitreAlbumsListBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(
            oldItem: Album,
            newItem: Album
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Album,
            newItem: Album
        ): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTitreAlbumsListBinding.inflate(layoutInflater, parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.data = currentItem
        holder.binding.executePendingBindings()
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }


}