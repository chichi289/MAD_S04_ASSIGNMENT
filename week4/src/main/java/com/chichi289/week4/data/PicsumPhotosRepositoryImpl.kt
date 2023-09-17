package com.chichi289.week4.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chichi289.week4.data.remote.PicsumPhotosService
import com.chichi289.week4.data.remote.model.UserDetail
import com.chichi289.week4.domain.PicsumPhotosRepository
import com.chichi289.week4.presentation.user_detail.UserDetailDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PicsumPhotosRepositoryImpl @Inject constructor(
    private val picsumPhotosService: PicsumPhotosService
) : PicsumPhotosRepository {
    override fun getPhotos(limit: Int): Flow<PagingData<UserDetail>> =
        Pager(config = PagingConfig(pageSize = 10)) {
            UserDetailDataSource(picsumPhotosService = picsumPhotosService)
        }.flow
}