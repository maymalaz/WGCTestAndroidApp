package com.example.wgctestandroidapp.presentation

import com.example.wgctestandroidapp.domain.Album

sealed class TitleListAlbumScreenState(
    val instruction: String? = null,
    var titreList: List<String> ?=null

) {
    object Idle : TitleListAlbumScreenState()
    object NoInternet : TitleListAlbumScreenState()
    class GetAlbumsInProgress() : TitleListAlbumScreenState()

    class GetAlbumsSuccessful(titreList:List<String> ): TitleListAlbumScreenState(titreList = titreList)

    class GetAlbumsFailed(instruction: String) :
        TitleListAlbumScreenState(instruction = instruction)

    class OtherError(instruction: String) : TitleListAlbumScreenState(instruction = instruction)
}