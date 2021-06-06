package com.yofi.moviecatalogue.data.source.remote

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.yofi.moviecatalogue.data.DataSource
import com.yofi.moviecatalogue.data.NetworkBoundResource
import com.yofi.moviecatalogue.data.Repository
import com.yofi.moviecatalogue.data.source.local.LocalDataSource
import com.yofi.moviecatalogue.data.source.local.entities.MovieEntity
import com.yofi.moviecatalogue.data.source.local.entities.TvShowEntity
import com.yofi.moviecatalogue.data.source.response.ItemMovie
import com.yofi.moviecatalogue.data.source.response.ItemTvShow
import com.yofi.moviecatalogue.utils.AppExecutors
import com.yofi.moviecatalogue.vo.Resource

class FakeRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    DataSource {

    companion object {
        @Volatile
        private var instance: FakeRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): FakeRepository =
            instance ?: synchronized(this) {
                instance ?: FakeRepository(
                    remoteData,
                    localData,
                    appExecutors
                ).apply { instance = this }
            }
    }

    override fun getMovie(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<ItemMovie>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ItemMovie>>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: List<ItemMovie>) {
                val listMovie = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id,
                        response.originalTitle,
                        response.posterPath,
                        response.releaseDate,
                        response.voteAverage,
                        response.overview,
                    )
                    listMovie.add(movie)
                }
                localDataSource.insertListMovie(listMovie)
            }
        }.asLiveData()
    }

    override fun getTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<ItemTvShow>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvshow(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ItemTvShow>>> =
                remoteDataSource.getTvShows()

            override fun saveCallResult(data: List<ItemTvShow>) {
                val listTvShow = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.originalName,
                        response.posterPath,
                        response.firstAirDate,
                        response.voteAverage,
                        response.overview,
                    )
                    listTvShow.add(tvShow)
                }
                localDataSource.insertListTvShow(listTvShow)
            }
        }.asLiveData()
    }

    override fun getMovieById(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, ItemMovie>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getMovieById(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<ItemMovie>> =
                remoteDataSource.getMovieById(movieId)

            override fun saveCallResult(data: ItemMovie) {
                val movie = MovieEntity(
                    data.id,
                    data.originalTitle,
                    data.posterPath,
                    data.releaseDate,
                    data.voteAverage,
                    data.overview,
                )
                localDataSource.insertMovie(movie)
            }
        }.asLiveData()
    }

    override fun getTvShowById(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, ItemTvShow>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getTvshowById(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<ItemTvShow>> =
                remoteDataSource.getTvShowById(tvShowId)

            override fun saveCallResult(data: ItemTvShow) {
                val tvShow = TvShowEntity(
                    data.id,
                    data.originalName,
                    data.posterPath,
                    data.firstAirDate,
                    data.voteAverage,
                    data.overview,
                )
                localDataSource.insertTvshows(tvShow)
            }
        }.asLiveData()
    }

    override fun getMovieByName(q: String): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<ItemMovie>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovieByName(q), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ItemMovie>>> =
                remoteDataSource.getMoviesByName(q)

            override fun saveCallResult(data: List<ItemMovie>) {
                val listMovie = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id,
                        response.originalTitle,
                        response.posterPath,
                        response.releaseDate,
                        response.voteAverage,
                        response.overview,
                    )
                    listMovie.add(movie)
                }
                localDataSource.insertListMovie(listMovie)
            }
        }.asLiveData()
    }

    override fun getTvShowByName(q: String): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<ItemTvShow>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return LivePagedListBuilder(localDataSource.getTvShowByName(q), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ItemTvShow>>> =
                remoteDataSource.getTvShowsByName(q)

            override fun saveCallResult(data: List<ItemTvShow>) {
                val listTvShow = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.originalName,
                        response.posterPath,
                        response.firstAirDate,
                        response.voteAverage,
                        response.overview,
                    )
                    listTvShow.add(tvShow)
                }
                localDataSource.insertListTvShow(listTvShow)
            }
        }.asLiveData()
    }

    override fun getFavMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavMovies(), config).build()
    }

    override fun getFavTvshow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavTvshows(), config).build()
    }

    override fun setFavMovie(movie: MovieEntity, state: Boolean): Int {
        appExecutors.diskIO().execute { localDataSource.setFavMovie(movie, state) }
        return movie.id
    }

    override fun setFavTvshow(tvShow: TvShowEntity, state: Boolean): Int {
        appExecutors.diskIO().execute { localDataSource.setFavTvshow(tvShow, state) }
        return tvShow.id
    }
}
