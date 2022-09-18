package com.example.wgctestandroidapp.presentation

import com.example.wgctestandroidapp.domain.Album

sealed class TitleListAlbumScreenState(
    val instruction: String? = null,
    var titleList: List<Album> ?=null

) {
    object Idle : TitleListAlbumScreenState()
    object NoInternet : TitleListAlbumScreenState()
    class GetAlbumsInProgress() : TitleListAlbumScreenState()

    class GetAlbumsSuccessful(titleList:List<Album> ): TitleListAlbumScreenState(titleList = titleList)

    class GetAlbumsFailed(instruction: String) :
        TitleListAlbumScreenState(instruction = instruction)

    class OtherError(instruction: String) : TitleListAlbumScreenState(instruction = instruction)
}