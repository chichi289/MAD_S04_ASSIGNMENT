package com.chichi289.week4.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chichi289.week4.data.remote.PicsumPhotosService
import com.chichi289.week4.data.remote.model.Post
import com.chichi289.week4.domain.PicsumPhotosRepository
import com.chichi289.week4.presentation.post.PostsDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PicsumPhotosRepositoryImpl @Inject constructor(
    private val picsumPhotosService: PicsumPhotosService
) : PicsumPhotosRepository {
    override val posts: Flow<PagingData<Post>> =
        Pager(config = PagingConfig(pageSize = 9)) {
            PostsDataSource(picsumPhotosService = picsumPhotosService)
        }.flow
}