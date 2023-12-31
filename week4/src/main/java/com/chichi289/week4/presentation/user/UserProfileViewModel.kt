package com.chichi289.week4.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.chichi289.week4.data.remote.model.NetworkResult
import com.chichi289.week4.data.remote.model.Post
import com.chichi289.week4.data.remote.model.User
import com.chichi289.week4.domain.PicsumPhotosRepository
import com.chichi289.week4.domain.RandomDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val randomDataRepository: RandomDataRepository,
    picsumPhotosRepository: PicsumPhotosRepository
) : ViewModel() {

    private val _mutableUserStateFlow: MutableStateFlow<NetworkResult<User>> =
        MutableStateFlow(NetworkResult.Loading())
    val userStateFlow: StateFlow<NetworkResult<User>> = _mutableUserStateFlow

    val postsPagingFlow: Flow<PagingData<Post>> = picsumPhotosRepository
        .posts
        .cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            randomDataRepository.getUserData(1).collect { networkResult ->
                _mutableUserStateFlow.update {
                    networkResult
                }
            }
        }
    }

}