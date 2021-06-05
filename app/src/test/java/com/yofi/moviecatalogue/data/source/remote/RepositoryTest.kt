package com.yofi.moviecatalogue.data.source.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.yofi.moviecatalogue.data.source.local.Dummy
import com.yofi.moviecatalogue.ui.LiveDataTestUtil
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito
import org.mockito.Mockito.mock

class RepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val repository = FakeRepository(remote)
    private val movieResponses = Dummy.getDataMovie()
    private val movieId = movieResponses[0].id
    private val detailMovie = Dummy.getDataMovie()[0]
    private val tvShowResponses = Dummy.getDataTvShow()
    private val tvShowId = tvShowResponses[0].id
    private val detailTvShow = Dummy.getDataTvShow()[0]

    @Test
    fun getMovie() {
        runBlocking {
            Mockito.doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadMovieCallback).onAllMovieReceived(
                    movieResponses
                )
                null
            }.`when`(remote).getMovies(any())
        }

        val liveData = LiveDataTestUtil.getValue(repository.getMovie())

        runBlocking {
            verify(remote).getMovies(any())
        }

        assertNotNull(liveData)
        assertEquals(movieResponses.size.toLong(), liveData.size.toLong())
    }

    @Test
    fun getTvShow() {
        runBlocking {
            Mockito.doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback).onAllTvShowReceived(
                    tvShowResponses
                )
                null
            }.`when`(remote).getTvShows(any())
        }

        val liveData = LiveDataTestUtil.getValue(repository.getTvShow())

        runBlocking {
            verify(remote).getTvShows(any())
        }

        assertNotNull(liveData)
        assertEquals(tvShowResponses.size.toLong(), liveData.size.toLong())
    }

    @Test
    fun getTvShowById() {
        runBlocking {
            Mockito.doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadTvShowsByIdCallback)
                    .onTvShowsDetailReceived(detailTvShow)
                null
            }.`when`(remote).getTvShowDetail(eq(tvShowId), any())
        }

        val liveData = LiveDataTestUtil.getValue(repository.getTvShowById(movieId))

        runBlocking {
            verify(remote).getTvShowDetail(eq(tvShowId), any())
        }

        assertNotNull(liveData)
        assertEquals(detailTvShow.id, liveData.id)
    }

    @Test
    fun getMovieById() {
        runBlocking {
            Mockito.doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadMovieByIdCallback)
                    .onMovieDetailReceived(detailMovie)
                null
            }.`when`(remote).getMovieDetail(eq(movieId), any())
        }

        val liveData = LiveDataTestUtil.getValue(repository.getMovieById(movieId))

        runBlocking {
            verify(remote).getMovieDetail(eq(movieId), any())
        }

        assertNotNull(liveData)
        assertEquals(detailMovie.id, liveData.id)
    }
}