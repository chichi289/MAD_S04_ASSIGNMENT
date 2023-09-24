package com.chichi289.week5.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.chichi289.week5.data.remote.model.post.Post
import com.chichi289.week5.domain.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    postRepository: PostRepository
) : ViewModel() {

    val postsPagingFlow: Flow<PagingData<Post>> = postRepository
        .posts
        .cachedIn(viewModelScope)

}