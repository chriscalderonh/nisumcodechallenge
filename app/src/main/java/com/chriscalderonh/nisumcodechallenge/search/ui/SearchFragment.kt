package com.chriscalderonh.nisumcodechallenge.search.ui

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.chriscalderonh.nisumcodechallenge.R
import com.chriscalderonh.nisumcodechallenge.common.presentation.MviUi
import com.chriscalderonh.nisumcodechallenge.databinding.FragmentSearchBinding
import com.chriscalderonh.nisumcodechallenge.search.presentation.SearchViewModel
import com.chriscalderonh.nisumcodechallenge.search.presentation.di.DaggerSearchComponent
import com.chriscalderonh.nisumcodechallenge.search.presentation.model.UiSearch
import com.chriscalderonh.nisumcodechallenge.search.presentation.uistate.SearchUiState
import com.chriscalderonh.nisumcodechallenge.search.presentation.userintent.SearchUserIntent
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class SearchFragment : Fragment(),
    MviUi<SearchUserIntent, SearchUiState> {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var searchResultListAdapter: SearchResultListAdapter

    lateinit var binding: FragmentSearchBinding
    lateinit var searchPublish: PublishSubject<SearchUserIntent>

    private val searchViewModel: SearchViewModel? by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(SearchViewModel::class.java)
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
        searchPublish = PublishSubject.create()
        searchViewModel?.processUserIntents(userIntents())
        observeUiStates()
    }

    private fun observeUiStates() {
        searchViewModel?.liveData()
            ?.observe(this, Observer<SearchUiState> { uiState ->
                uiState?.let { renderUiStates(it) }
            })
    }

    override fun renderUiStates(uiState: SearchUiState) {
        setScreenForLoading(uiState.isLoading)
        setScreenForError(uiState.error)
        when (uiState) {
            is SearchUiState.SuccessGettingSearchResults -> setScreenForShowSearchResults(uiState.uiSearch)
            is SearchUiState.ErrorGettingSearchResults -> setScreenForError(uiState.error)
        }
    }

    private fun setScreenForLoading(loading: Boolean) {
        if (loading) {
            binding.lvSearchLoading.visibility = View.VISIBLE
        } else {
            binding.lvSearchLoading.visibility = View.GONE
        }
    }

    private fun setScreenForError(error: String?) {
        error?.let {
            binding.evSearchError.visibility = View.VISIBLE
        } ?: run {
            binding.evSearchError.visibility = View.GONE
        }
    }

    private fun setScreenForShowSearchResults(searchResults: UiSearch) {
        searchResults.results?.let { list ->
            searchResultListAdapter.searchItems = list
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        setupAdapter()
        setupListeners()
        return binding.root
    }

    private fun setupAdapter() {
        context?.let {
            val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            getDrawable(it, R.drawable.rv_line_divider)?.let { drawable ->
                itemDecoration.setDrawable(drawable)
                binding.rvResults.addItemDecoration(itemDecoration)
            }
        }
        binding.rvResults.adapter = searchResultListAdapter
    }

    private fun setupListeners() {
        binding.etSearchText.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                context?.let { hideKeyboard(it, binding.etSearchText) }
                val searchText = binding.etSearchText.text.toString()
                searchPublish.onNext(SearchUserIntent.InvokingSearchUserIntent(searchText, 0))
                true
            }
            false
        }
        binding.evSearchError.setClickListener { binding.evSearchError.visibility = View.GONE }
    }

    override fun userIntents(): Observable<SearchUserIntent> = searchUserIntent()

    private fun searchUserIntent(): Observable<SearchUserIntent> = searchPublish

    private fun hideKeyboard(context: Context, view: View) {
        try {
            val keyboard =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            keyboard.hideSoftInputFromWindow(view.windowToken, 0)
        } catch (e: Exception) {
        }
    }
}