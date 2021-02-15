package com.chriscalderonh.nisumcodechallenge.search.presentation.action

sealed class SearchMusicAction {

    data class GetSearchResultsMusicAction(val text: String, val page: Int) : SearchMusicAction()
}