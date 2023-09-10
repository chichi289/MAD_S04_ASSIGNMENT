package com.chichi289.week3.presentation.welcome_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chichi289.week3.data.model.User
import com.chichi289.week3.domain.DatabaseRepository
import com.chichi289.week3.domain.InMemoryUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val inMemoryUserRepository: InMemoryUserRepository,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    var dbUsers: StateFlow<List<User>> = databaseRepository.getAllUsers().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun saveUserToDatabase() {
        val users = inMemoryUserRepository.getUsers()
        databaseRepository.insertAll(*users.toTypedArray())
    }

}