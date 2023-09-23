package com.chichi289.week5.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chichi289.week5.data.remote.model.NetworkResult
import com.chichi289.week5.data.remote.model.user.User
import com.chichi289.week5.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _mutableUserStateFlow: MutableStateFlow<NetworkResult<User>> =
        MutableStateFlow(NetworkResult.Loading())
    val userStateFlow: StateFlow<NetworkResult<User>> = _mutableUserStateFlow

    fun getRandomUser() {
        viewModelScope.launch {
            userRepository.getRandomUser().collect { networkResult ->
                _mutableUserStateFlow.update {
                    networkResult
                }
            }
        }

    }

}