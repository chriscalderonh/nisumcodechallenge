package com.chriscalderonh.nisumcodechallenge.search.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.chriscalderonh.nisumcodechallenge.databinding.ViewResultItemBinding
import com.chriscalderonh.nisumcodechallenge.search.presentation.model.UiResult
import javax.inject.Inject

class SearchResultListAdapter @Inject constructor() :
    RecyclerView.Adapter<SearchResultListAdapter.ViewHolder>() {

    var searchItems: List<UiResult> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ViewResultItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = searchItems[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = searchItems.size

    inner class ViewHolder(private val binding: ViewResultItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UiResult) {
            binding.apply {
                tvItemArtistName.text = item.artistName
                tvItemAlbumName.text = item.collectionName
                tvItemName.text = item.trackName
                clItem.setOnClickListener { view ->
                        val directions = SearchFragmentDirections.actionSearchFragmentToAlbumTracksFragment(item.collectionId.toString())
                        view.findNavController().navigate(directions)
                }
            }
        }
    }
}

