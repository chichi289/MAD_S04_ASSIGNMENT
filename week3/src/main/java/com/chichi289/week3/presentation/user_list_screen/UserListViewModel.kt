package com.chichi289.week3.presentation.user_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chichi289.week3.data.model.User
import com.chichi289.week3.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _users: MutableStateFlow<List<User>> = MutableStateFlow(emptyList())
    val users: StateFlow<List<User>> = _users

    fun getUsers() {
        viewModelScope.launch {
            // Simulate network request
            delay(1500)
            _users.update {
                userRepository.getUsers()
            }
        }
    }

}