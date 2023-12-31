package com.chichi289.week5.presentation.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chichi289.week5.data.remote.model.NetworkResult
import com.chichi289.week5.data.remote.model.post.Post
import com.chichi289.week5.domain.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    private val _mutablePostStateFlow =
        MutableStateFlow<NetworkResult<Post>>(NetworkResult.Loading)
    val postStateFlow: StateFlow<NetworkResult<Post>> = _mutablePostStateFlow


    private val _mutableDeletePostStateFlow =
        MutableStateFlow<NetworkResult<Unit>>(NetworkResult.Loading)
    val deletePostStateFlow: StateFlow<NetworkResult<Unit>> = _mutableDeletePostStateFlow

    fun getPostDetail(postId: Long) {
        viewModelScope.launch {
            postRepository.getPostDetail(postId).collect {
                _mutablePostStateFlow.value = it
            }
        }
    }

    fun deletePost(postId: Long, userId: Long) {
        viewModelScope.launch {
            postRepository.deletePost(postId, userId).collect {
                _mutableDeletePostStateFlow.value = it
            }
        }
    }

}