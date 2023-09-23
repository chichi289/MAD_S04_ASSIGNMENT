package com.chichi289.week5.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chichi289.week5.data.remote.model.NetworkResult
import com.chichi289.week5.data.remote.model.user.User
import com.chichi289.week5.domain.LocalRepository
import com.chichi289.week5.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val localRepository: LocalRepository
) : ViewModel() {

    val userFlow = localRepository.getUser()

    fun getRandomUser() {
        viewModelScope.launch {
            userRepository.getRandomUser().collect { networkResult ->
                // Save user to room db
                if (networkResult is NetworkResult.Success) {
                    networkResult.data?.let { localRepository.insertUser(it) }
                }
            }
        }

    }

}