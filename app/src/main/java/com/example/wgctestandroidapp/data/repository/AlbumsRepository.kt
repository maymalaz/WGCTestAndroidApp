package com.example.wgctestandroidapp.data.repository

import android.util.Log
import com.example.wgctestandroidapp.data.beans.toAlbums
import com.example.wgctestandroidapp.data.endpoints.AlbumsEndpoints
import com.example.wgctestandroidapp.domain.common.DataState
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlbumsRepository @Inject constructor(
    private val albumsEndPoints: AlbumsEndpoints,
)
{
    fun getTitles() = flow{
        emit(DataState.Loading(null))

        val result = albumsEndPoints.getListAlbums()
        Log.d("titles",result.toString())

        if (result.success) {
            emit(DataState.Success(result.data!!.map { it.toAlbums() }))
            Log.d("titles&",result.data.toString())
        }

        else {
            emit(DataState.Error<Unit?>(result.message ?: "Unknown Error"))
        }


    }
}