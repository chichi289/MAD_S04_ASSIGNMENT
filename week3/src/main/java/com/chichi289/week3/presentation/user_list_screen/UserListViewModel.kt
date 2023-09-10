package com.chichi289.week3.presentation.user_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chichi289.week3.domain.DatabaseRepository
import com.chichi289.week3.domain.InMemoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository,
    private val inMemoryRepository: InMemoryRepository,
) : ViewModel() {

    var users = databaseRepository.getAllUsers()

    fun addOneUserToDb() {
        val user = inMemoryRepository.getUsers(1)
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.insertAll(*user.toTypedArray())
        }
    }

}