package com.chichi289.week5.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chichi289.week5.data.remote.model.NetworkResult
import com.chichi289.week5.data.remote.model.post.Post
import com.chichi289.week5.data.remote.model.user.User
import com.chichi289.week5.domain.LocalRepository
import com.chichi289.week5.domain.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val postRepository: PostRepository
) : ViewModel() {

    private val _mutableUserStateFlow = MutableStateFlow<User?>(null)
    val userStateFlow: StateFlow<User?> = _mutableUserStateFlow

    private val _mutableUserPostsMutableStateFlow =
        MutableStateFlow<NetworkResult<List<Post>>>(NetworkResult.Loading())

    val userPostsStateFlow: StateFlow<NetworkResult<List<Post>>> = _mutableUserPostsMutableStateFlow

    init {
        viewModelScope.launch {
            localRepository.getUser().collect {
                if(it.isNotEmpty()){
                    _mutableUserStateFlow.value = it.first()
                }
            }
        }
    }

    fun getPosts(userId: Int) {
        viewModelScope.launch {
            postRepository.getPostsByUserId(userId).collect {
                _mutableUserPostsMutableStateFlow.value = it
            }
        }
    }

}