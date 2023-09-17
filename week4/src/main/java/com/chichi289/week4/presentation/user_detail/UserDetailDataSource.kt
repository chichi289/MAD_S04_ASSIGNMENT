package com.chichi289.week4.presentation.user_detail

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chichi289.week4.data.remote.PicsumPhotosService
import com.chichi289.week4.data.remote.model.UserDetail


const val FIRST_PAGE = 1

class UserDetailDataSource(
    private val picsumPhotosService: PicsumPhotosService
) : PagingSource<Int, UserDetail>() {
    override fun getRefreshKey(state: PagingState<Int, UserDetail>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserDetail> {
        return try {
            val page = params.key ?: FIRST_PAGE

            val response = picsumPhotosService.getUserDetails(
                page = page,
                limit = params.loadSize
            )

            if (response.isSuccessful && response.body() != null) {
                val items = response.body()!!
                LoadResult.Page(
                    data = items,
                    prevKey = if (page == FIRST_PAGE) null else page - 1,
                    nextKey = if (items.isEmpty()) null else page + 1
                )
            } else {
                LoadResult.Error(Exception(response.errorBody().toString()))
            }


        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}