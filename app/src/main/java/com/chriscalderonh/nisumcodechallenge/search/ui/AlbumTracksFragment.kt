package com.chriscalderonh.nisumcodechallenge.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.request.target.Target
import com.chriscalderonh.nisumcodechallenge.R
import com.chriscalderonh.nisumcodechallenge.common.presentation.MviUi
import com.chriscalderonh.nisumcodechallenge.common.ui.GlideApp
import com.chriscalderonh.nisumcodechallenge.databinding.FragmentAlbumTracksBinding
import com.chriscalderonh.nisumcodechallenge.search.presentation.CollectionViewModel
import com.chriscalderonh.nisumcodechallenge.search.presentation.di.DaggerSearchComponent
import com.chriscalderonh.nisumcodechallenge.search.presentation.model.UiCollection
import com.chriscalderonh.nisumcodechallenge.search.presentation.uistate.CollectionUiState
import com.chriscalderonh.nisumcodechallenge.search.presentation.userintent.CollectionUserIntent
import io.reactivex.Observable
import javax.inject.Inject

class AlbumTracksFragment : Fragment(),
    MviUi<CollectionUserIntent, CollectionUiState> {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var albumTracksAdapter: AlbumTracksAdapter

    lateinit var binding: FragmentAlbumTracksBinding
    private val args: AlbumTracksFragmentArgs by navArgs()

    private val collectionViewModel: CollectionViewModel? by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(CollectionViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInjection()
        subscribeUiStatesAndProcessUserIntents()
    }

    private fun setupInjection() {
        DaggerSearchComponent.builder()
            .build()
            .inject(this)
    }

    private fun subscribeUiStatesAndProcessUserIntents() {
        collectionViewModel?.processUserIntents(userIntents())
        observeUiStates()
    }

    private fun observeUiStates() {
        collectionViewModel?.liveData()
            ?.observe(this, { uiState ->
                uiState?.let { renderUiStates(it) }
            })
    }

    override fun renderUiStates(uiState: CollectionUiState) {
        setScreenForLoading(uiState.isLoading)
        setScreenForError(uiState.error)
        when (uiState) {
            is CollectionUiState.SuccessGettingAlbumResults -> setScreenForShowAlbumTracks(uiState.uiCollection)
            is CollectionUiState.ErrorGettingAlbumResults -> setScreenForError(uiState.error)
        }
    }

    private fun setScreenForLoading(loading: Boolean) {
        if (loading) {
            binding.lvAlbumTracksLoading.visibility = View.VISIBLE
        } else {
            binding.lvAlbumTracksLoading.visibility = View.GONE
        }
    }

    private fun setScreenForError(error: String?) {
        error?.let {
            binding.evAlbumTracksError.visibility = View.VISIBLE
        } ?: run {
            binding.evAlbumTracksError.visibility = View.GONE
        }
    }

    private fun setScreenForShowAlbumTracks(collectionResponse: UiCollection) {
        collectionResponse.tracks?.let { list ->
            albumTracksAdapter.albumTracks = list
        }
        binding.apply {
            collectionResponse.collection?.let {
                tvAlbumName.text = it.collectionName
                tvArtistName.text = it.artistName
                context?.let { context ->
                    GlideApp.with(context)
                        .load(it.artworkUrl100)
                        .override(Target.SIZE_ORIGINAL)
                        .into(ivAlbumArtwork)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumTracksBinding.inflate(inflater, container, false)
        setupAdapter()
        return binding.root
    }

    private fun setupAdapter() {
        context?.let {
            val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            AppCompatResources.getDrawable(it, R.drawable.rv_line_divider)?.let { drawable ->
                itemDecoration.setDrawable(drawable)
                binding.rvAlbumTracks.addItemDecoration(itemDecoration)
            }
        }
        binding.rvAlbumTracks.adapter = albumTracksAdapter
    }

    override fun userIntents(): Observable<CollectionUserIntent> = collectionUserIntent()

    private fun collectionUserIntent(): Observable<CollectionUserIntent> = Observable.just(
        CollectionUserIntent.InitialUserIntent(args.collectionId)
    )

}