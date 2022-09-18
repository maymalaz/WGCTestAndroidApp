package com.example.wgctestandroidapp.data.endpoints

import com.example.wgctestandroidapp.data.beans.BeanResponseAlbum
import com.example.wgctestandroidapp.domain.common.HttpResult
import retrofit2.http.GET


interface AlbumsEndpoints {

    @GET("/img/shared/technical-test.json")
    suspend fun getListAlbums(): HttpResult<List<BeanResponseAlbum>>


}
