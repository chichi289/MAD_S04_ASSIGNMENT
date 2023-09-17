package com.chichi289.week4.presentation.post

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chichi289.week4.data.remote.PicsumPhotosService
import com.chichi289.week4.data.remote.model.Post
import com.chichi289.week4.utils.log


const val FIRST_PAGE = 1

class PostsDataSource(
    private val picsumPhotosService: PicsumPhotosService
) : PagingSource<Int, Post>() {
    override fun getRefreshKey(state: PagingState<Int, Post>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val page = params.key ?: FIRST_PAGE

            val items: List<Post> = picsumPhotosService.getPosts(
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