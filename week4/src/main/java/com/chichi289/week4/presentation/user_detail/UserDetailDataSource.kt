package com.chichi289.week4.presentation.user_detail

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chichi289.week4.data.remote.PicsumPhotosService
import com.chichi289.week4.data.remote.model.UserDetail
import com.chichi289.week4.utils.log


const val FIRST_PAGE = 1

class UserDetailDataSource(
    private val picsumPhotosService: PicsumPhotosService
) : PagingSource<Int, UserDetail>() {
    override fun getRefreshKey(state: PagingState<Int, UserDetail>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserDetail> {
        return try {
            val page = params.key ?: FIRST_PAGE

            val items: List<UserDetail> = picsumPhotosService.getUserDetails(
                page = page,
                limit = params.loadSize
            )

            if (items.isNotEmpty()) {
                LoadResult.Page(
                    data = items,
                    prevKey = if (page == FIRST_PAGE) null else page - 1,
                    nextKey = if (items.isEmpty()) null else page + 1
                )
            } else {
                LoadResult.Error(Exception("Response is empty"))
            }


        } catch (e: Exception) {
            e.message.log()
            LoadResult.Error(e)
        }
    }
}