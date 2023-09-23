package com.chichi289.week5.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chichi289.week5.data.remote.model.user.User
import com.chichi289.week5.domain.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _mutableUserStateFlow = MutableStateFlow<User?>(null)
    val userStateFlow: StateFlow<User?> = _mutableUserStateFlow

    init {
        viewModelScope.launch {
            localRepository.getUser().collect {
                _mutableUserStateFlow.value = it.first()
            }
        }
    }
}