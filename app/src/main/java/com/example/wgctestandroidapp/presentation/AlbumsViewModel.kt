package com.example.wgctestandroidapp.presentation

import android.util.Log
import androidx.lifecycle.*
import com.example.wgctestandroidapp.data.repository.AlbumsRepository
import com.example.wgctestandroidapp.domain.Album
import com.example.wgctestandroidapp.domain.common.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val repositoryAlbums: AlbumsRepository) :
    ViewModel(),
    LifecycleObserver {


    private val _getTitlesScreenState: MutableLiveData<TitleListAlbumScreenState> =
        MutableLiveData<TitleListAlbumScreenState>(TitleListAlbumScreenState.Idle)

    val getTitlesScreenState: LiveData<TitleListAlbumScreenState>
        get() = this._getTitlesScreenState

    private val albumsLiveData = MutableLiveData<List<String>>()
    val _albums: LiveData<List<String>> = albumsLiveData



    fun changeScreenState(newState: TitleListAlbumScreenState) {
        when (newState) {
            TitleListAlbumScreenState.Idle -> {
            }
            TitleListAlbumScreenState.NoInternet-> {
            }
            is TitleListAlbumScreenState.OtherError -> {
            }
            is TitleListAlbumScreenState.GetAlbumsInProgress -> {
                viewModelScope.launch {
                    repositoryAlbums.getTitles(
                    ).onEach {
                        when (it) {
                            is DataState.Success -> {
                                _getTitlesScreenState.value =
                                    TitleListAlbumScreenState.GetAlbumsSuccessful(it.data!! as List<String>)
                                Log.d("Titres1", it.data.toString())
                                albumsLiveData.value= it.data as List<String>

                            }
                            is DataState.Error -> {
                                _getTitlesScreenState.value =
                                    TitleListAlbumScreenState.GetAlbumsFailed(it.message!!)
                            }
                            else -> {}
                        }
                    }.launchIn(viewModelScope)
                }
            }
            else -> {}
        }
    }

}


