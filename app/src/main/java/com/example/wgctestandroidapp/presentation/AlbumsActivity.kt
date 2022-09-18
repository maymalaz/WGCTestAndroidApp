package com.example.wgctestandroidapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wgctestandroidapp.data.common.utils.logD
import com.example.wgctestandroidapp.databinding.ActivityMainBinding
import com.example.wgctestandroidapp.domain.Album
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsActivity: AppCompatActivity() {
    private val TAG = "MainActivityTag"

    private val titreListAdapter by lazy { AlbumListAdapter() }
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<AlbumsViewModel>()

    var Titlelist: MutableList<Album> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initRV()

        viewModel.getTitlesScreenState.observe(this) {
            handleState(it)
        }
        viewModel._albums.observe(this){


            Log.d(TAG, it.toString())
        }

        viewModel.changeScreenState(TitleListAlbumScreenState.GetAlbumsInProgress())


    }


    private fun initRV() {
        binding.recycleTitreAlbum.adapter = titreListAdapter
        binding.recycleTitreAlbum.layoutManager= LinearLayoutManager(this)

        val a= Album(1,1,"officiis exercitationem quia","","")
        Titlelist!!.add(a)
        Titlelist!!.add(a)
        Titlelist!!.add(a)
        Titlelist!!.add(a)

        titreListAdapter.submitList(Titlelist)

    }

    private fun handleState(state: TitleListAlbumScreenState) {
        when (state) {
            TitleListAlbumScreenState.Idle -> {
                logD("---->>> In Idle")
            }
            TitleListAlbumScreenState.NoInternet -> {
                logD("---->>> In NoInternet")
            }
            is TitleListAlbumScreenState.GetAlbumsInProgress -> {
                logD("---->>> In TitleListInProgress")

            }
            is TitleListAlbumScreenState.GetAlbumsFailed -> {
                logD("---->>> In TitleListFailed")
            }
            is TitleListAlbumScreenState.OtherError -> {
                logD("---->>> In OtherError")
            }
            is TitleListAlbumScreenState.GetAlbumsSuccessful -> {
                logD("---->>> In TitleListSuccessful" + state.titleList)


            }
        }
    }

}