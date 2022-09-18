package com.example.wgctestandroidapp.data

import com.example.wgctestandroidapp.data.common.DI.RetrofitDi
import com.example.wgctestandroidapp.data.endpoints.AlbumsEndpoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [RetrofitDi::class])
@InstallIn(SingletonComponent::class)
class AlbumsModule {
    @Singleton
    @Provides
    fun provideChatApi(retrofit: Retrofit): AlbumsEndpoints {
        return retrofit.create(AlbumsEndpoints::class.java)
    }

}
