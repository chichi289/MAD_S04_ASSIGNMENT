package com.chichi289.assignments.presentation.screens.week2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chichi289.assignments.data.UserRepositoryImpl
import com.chichi289.assignments.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userRepository: UserRepositoryImpl
) : ViewModel() {

    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    val users: LiveData<List<User>> = _users

    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            _users.postValue(userRepository.getUsers())
        }
    }

}