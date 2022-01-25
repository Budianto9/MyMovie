package com.example.mymovie.core.data.source.remote.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mymovie.core.data.source.remote.network.Api
import com.example.mymovie.core.data.source.remote.response.MovieResponse

class MovieDataSource(
    private val apiService: Api,
    private val apiKey: String,
) : PagingSource<Int, MovieResponse>() {

    // The refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> {
        val startPage = 1
        val position = params.key ?: startPage

        return try {
            val response = apiService.getPopularMovies(apiKey, position)

            if (response.isSuccessful) {
                if (response.body() != null) {
                    val body = response.body()!!

                    val nextKey = if (body.results.isEmpty())
                        null
                    else
                    // initial load size = 3 * NETWORK_PAGE_SIZE
                    // ensure we're not requesting duplicating items, at the 2nd request
                        position + 1

                    LoadResult.Page(
                        data = body.results,
                        prevKey = if (position == startPage) null else position - 1,
                        nextKey = nextKey
                    )
                } else
                    LoadResult.Error(Exception("Terjadi kesalahan"))
            } else
                LoadResult.Error(Exception("Terjadi kesalahan"))
        } catch (e: Exception){
            LoadResult.Error(Exception("Terjadi Kesalahan"))
        }
    }
}