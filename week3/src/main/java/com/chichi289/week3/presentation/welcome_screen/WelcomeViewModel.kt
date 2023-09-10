package com.chichi289.week3.presentation.welcome_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chichi289.week3.domain.DatabaseRepository
import com.chichi289.week3.domain.InMemoryRepository
import com.chichi289.week3.domain.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val inMemoryUserRepository: InMemoryRepository,
    private val databaseRepository: DatabaseRepository,
    private val localRepository: LocalRepository
) : ViewModel() {

    fun saveUserToDatabase() {
        val users = inMemoryUserRepository.getUsers()
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.insertAll(*users.toTypedArray())
            localRepository.setUserAddedToDb(true)
        }
    }

}