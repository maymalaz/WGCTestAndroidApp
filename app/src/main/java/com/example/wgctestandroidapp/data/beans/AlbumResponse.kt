package com.example.wgctestandroidapp.data.beans

import android.os.Parcelable
import com.example.wgctestandroidapp.domain.Album
import com.example.wgctestandroidapp.domain.mappers.NetworkModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BeanResponseAlbum(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
) : NetworkModel, Parcelable




fun BeanResponseAlbum.toAlbums(): Album {
    return Album(
        id = id,
        albumId = albumId,
        title = title,
        url = url,
        thumbnailUrl = thumbnailUrl

    )
}

