package com.chriscalderonh.nisumcodechallenge.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chriscalderonh.nisumcodechallenge.databinding.ViewTrackItemBinding
import com.chriscalderonh.nisumcodechallenge.search.presentation.model.UiCollectionResult
import javax.inject.Inject

class AlbumTracksAdapter @Inject constructor() :
    RecyclerView.Adapter<AlbumTracksAdapter.ViewHolder>() {

    var albumTracks: List<UiCollectionResult> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ViewTrackItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = albumTracks[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = albumTracks.size

    inner class ViewHolder(private val binding: ViewTrackItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UiCollectionResult) {
            binding.apply {
                tvTrackNumber.text = item.trackNumber.toString()
                tvTrackName.text = item.trackName
            }
        }
    }
}

