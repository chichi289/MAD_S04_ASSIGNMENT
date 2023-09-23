package com.chichi289.week5.presentation.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chichi289.week5.data.remote.model.post.Post
import com.chichi289.week5.data.remote.model.post.PostPagingData
import com.chichi289.week5.data.remote.model.post.PostsResponse
import com.chichi289.week5.data.remote.service.PostService
import retrofit2.Response
import java.net.UnknownHostException


const val FIRST_PAGE = 0

class PostsDataSource(
    private val postService: PostService
) : PagingSource<Int, Post>() {
    override fun getRefreshKey(state: PagingState<Int, Post>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val page = params.key ?: FIRST_PAGE

            val response: Response<PostsResponse> = postService.getPosts(
                page = page,
                size = params.loadSize
            )

            if (response.isSuccessful) {
                val postsResponse: PostsResponse? = response.body()
                val postPagingData: PostPagingData? = postsResponse?.data
                val posts = postPagingData?.posts
                val hasNextPage = postPagingData?.hasNextPage ?: false
                if (!posts.isNullOrEmpty()) {
                    LoadResult.Page(
                        data = posts,
                        prevKey = if (page == FIRST_PAGE) null else page - 1,
                        nextKey = if (hasNextPage) page + 1 else null
                    )
                } else {
                    LoadResult.Page(
                        data = emptyList(),
                        prevKey = null,
                        nextKey = null
                    )
                }
            }else{
                LoadResult.Error(Exception(response.message()))
            }

        } catch (e: Exception) {
            if (e is UnknownHostException) {
                LoadResult.Error(Exception("No internet connection"))
            } else {
                LoadResult.Error(e)
            }
        }
    }
}