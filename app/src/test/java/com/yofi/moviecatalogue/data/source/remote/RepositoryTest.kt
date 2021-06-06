package com.yofi.moviecatalogue.data.source.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import com.yofi.moviecatalogue.data.source.local.LocalDataSource
import com.yofi.moviecatalogue.data.source.local.entities.MovieEntity
import com.yofi.moviecatalogue.data.source.local.entities.TvShowEntity
import com.yofi.moviecatalogue.utils.Dummy
import com.yofi.moviecatalogue.utils.LiveDataTestUtil
import com.yofi.moviecatalogue.utils.PagedListUtil
import com.yofi.moviecatalogue.utils.AppExecutors
import com.yofi.moviecatalogue.utils.TestExecutor
import com.yofi.moviecatalogue.vo.Resource
import junit.framework.Assert
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.*

class RepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val testExecutors: AppExecutors = AppExecutors(TestExecutor(), TestExecutor(), TestExecutor())
    private val repository = FakeRepository(remote,local,appExecutors)
    private val movieResponses = Dummy.getDataMovie()
    private val movieId = movieResponses[0].id
    private val tvShowResponses = Dummy.getDataTvShow()
    private val tvShowId = tvShowResponses[0].id

    @Test
    fun getMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        repository.getMovie()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(Dummy.getDataMovie()))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvshow()).thenReturn(dataSourceFactory)
        repository.getTvShow()

        val tvshowEntities = Resource.success(PagedListUtil.mockPagedList((Dummy.getDataTvShow())))
        verify(local).getAllTvshow()
        Assert.assertNotNull(tvshowEntities.data)
        assertEquals(tvShowResponses.size.toLong(), tvshowEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShowById() {
        val dummyDetailtvshow = MutableLiveData<TvShowEntity>()
        dummyDetailtvshow.value = Dummy.getDataTvShow()[0]
        `when`(local.getTvshowById(tvShowId)).thenReturn(
            dummyDetailtvshow
        )

        val tvShowDetailEntitiesContent =
            LiveDataTestUtil.getValue(repository.getTvShowById(tvShowId))
        verify(local)
            .getTvshowById(tvShowId)

        assertNotNull(tvShowDetailEntitiesContent)
    }

    @Test
    fun getMovieById() {
        val dummyDetailmovie = MutableLiveData<MovieEntity>()
        dummyDetailmovie.value = Dummy.getDataMovie()[0]
        `when`(local.getMovieById(movieId)).thenReturn(dummyDetailmovie)

        val movieDetailEntitiesContent =
            LiveDataTestUtil.getValue(repository.getMovieById(movieId))
        verify(local)
            .getMovieById(movieId)

        Assert.assertNotNull(movieDetailEntitiesContent)
    }

    @Test
    fun getMovieByName() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovieByName("a")).thenReturn(dataSourceFactory)
        repository.getMovieByName("a")

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(Dummy.getDataMovie()))
        verify(local).getMovieByName("a")
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShowByName() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getTvShowByName("a")).thenReturn(dataSourceFactory)
        repository.getTvShowByName("a")

        val tvshowEntities = Resource.success(PagedListUtil.mockPagedList((Dummy.getDataTvShow())))
        verify(local).getTvShowByName("a")
        Assert.assertNotNull(tvshowEntities.data)
        assertEquals(tvShowResponses.size.toLong(), tvshowEntities.data?.size?.toLong())
    }

    @Test
    fun setFavoriteMovie(){
        `when`(appExecutors.diskIO()).thenReturn(testExecutors.diskIO())
        val movie: MovieEntity = Dummy.getDataMovie()[0]

        // set true favorite
        `when`(local.setFavMovie(movie, true)).thenReturn(movie.id)
        repository.setFavMovie(movie, true)
        verify(local, times(1)).setFavMovie(movie, true)

        // set false favorite
        `when`(local.setFavMovie(movie, false)).thenReturn(movie.id)
        repository.setFavMovie(movie, false)
        verify(local, times(1)).setFavMovie(movie, true)
    }

    @Test
    fun setFavoriteTvShow(){
        `when`(appExecutors.diskIO()).thenReturn(testExecutors.diskIO())
        val tvShow: TvShowEntity = Dummy.getDataTvShow()[0]

        // set true favorite
        `when`(local.setFavTvshow(tvShow, true)).thenReturn(tvShow.id)
        repository.setFavTvshow(tvShow, true)
        verify(local, times(1)).setFavTvshow(tvShow, true)

        // set false favorite
        `when`(local.setFavTvshow(tvShow, false)).thenReturn(tvShow.id)
        repository.setFavTvshow(tvShow, false)
        verify(local, times(1)).setFavTvshow(tvShow, true)
    }
}