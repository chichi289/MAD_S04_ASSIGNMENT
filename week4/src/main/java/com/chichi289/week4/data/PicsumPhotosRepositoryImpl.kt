package com.chichi289.week4.data

import com.chichi289.week4.data.remote.PicsumPhotosService
import com.chichi289.week4.data.remote.model.NetworkResult
import com.chichi289.week4.domain.PicsumPhotosRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PicsumPhotosRepositoryImpl @Inject constructor(
    private val picsumPhotosService: PicsumPhotosService
) : PicsumPhotosRepository {
    override fun getPhotos(page: Int, limit: Int): Flow<NetworkResult<Unit>> {
        TODO("Implementation pending")
    }
}