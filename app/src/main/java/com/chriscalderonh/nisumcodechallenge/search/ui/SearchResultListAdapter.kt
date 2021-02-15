package com.chriscalderonh.nisumcodechallenge.search.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    var onItemClickListener: View.OnClickListener = View.OnClickListener {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ViewResultItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = searchItems[position]
        holder.bind(item, onItemClickListener)
    }

    override fun getItemCount(): Int = searchItems.size

    inner class ViewHolder(private val binding: ViewResultItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UiResult, clickItemListener: View.OnClickListener) {
            binding.apply {
                tvItemArtistName.text = item.artistName
                tvItemAlbumName.text = item.collectionName
                tvItemName.text = item.trackName
                clItem.setOnClickListener { clickItemListener }
            }
        }
    }
}

