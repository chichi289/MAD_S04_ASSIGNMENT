package com.chichi289.week3.presentation.welcome_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chichi289.week3.data.model.User
import com.chichi289.week3.domain.DatabaseRepository
import com.chichi289.week3.domain.InMemoryRepository
import com.chichi289.week3.domain.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val inMemoryUserRepository: InMemoryRepository,
    private val databaseRepository: DatabaseRepository,
    private val localRepository: LocalRepository
) : ViewModel() {

    var dbUsers: StateFlow<List<User>> = databaseRepository.getAllUsers().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    val isLoggedIn: StateFlow<Boolean> = localRepository.isUserStoredInDb.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = false
    )

    fun saveUserToDatabase() {
        val users = inMemoryUserRepository.getUsers()
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.insertAll(*users.toTypedArray())
            localRepository.setUserAddedToDb(true)
        }
    }

}