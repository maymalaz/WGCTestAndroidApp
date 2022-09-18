package com.example.wgctestandroidapp.data.repository

import com.example.wgctestandroidapp.BaseTest
import com.example.wgctestandroidapp.data.beans.BeanResponseAlbum
import com.example.wgctestandroidapp.data.endpoints.AlbumsEndpoints
import com.example.wgctestandroidapp.domain.common.DataState
import com.example.wgctestandroidapp.domain.common.HttpResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response


internal class AlbumsRepositoryTest
    : BaseTest() {
    private val albumsEndPoints = Mockito.mock(AlbumsEndpoints::class.java)
    private val albumsRepository = AlbumsRepository(albumsEndPoints)

    @Test
    fun `when Albums endpoint success it returns valid album list`() = runBlockingTest {

        val album1 = BeanResponseAlbum(
            id = 1,
            albumId = 1,
            title = "neque eum provident et inventore sed ipsam dignissimos quo",
            url = "https://via.placeholder.com/600/c4084a",
            thumbnailUrl = "https://via.placeholder.com/150/c4084a"
        )
        val album2 = BeanResponseAlbum(
            id = 2,
            albumId = 2,
            title = "neque eum provident et inventore sed ipsam dignissimos quo",
            url = "https://via.placeholder.com/600/c4084a",
            thumbnailUrl = "https://via.placeholder.com/150/c4084a"
        )
        val albumsResponse = listOf(album1, album2)


        val response = Response.success(HttpResult(data = albumsResponse))

        Mockito.`when`(albumsRepository.getTitles())
            .thenReturn(response as Flow<DataState<out Any?>>  )

        // When
        val successState = albumsRepository.getTitles()

        // Then
        Assert.assertThat(
            successState.first(),
            CoreMatchers.instanceOf(DataState.Success::class.java)
        )
        val actualAlbums = (successState.first()  as DataState.Success<BeanResponseAlbum>).data

        Assert.assertEquals(1, actualAlbums!!.albumId)
        Assert.assertEquals(1, actualAlbums!!.id)
        Assert.assertEquals("neque eum provident et inventore sed ipsam dignissimos quo", actualAlbums!!.title)
        Assert.assertEquals("https://via.placeholder.com/600/c4084a", actualAlbums!!.url)
        Assert.assertEquals("https://via.placeholder.com/150/c4084a", actualAlbums!!.thumbnailUrl)

    }

}
