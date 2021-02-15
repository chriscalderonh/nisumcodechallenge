package com.chriscalderonh.nisumcodechallenge.search.presentation.action

sealed class GetAlbumTracksAction {

    data class GetListOfTracksAction(val collectionId: String) : GetAlbumTracksAction()
}