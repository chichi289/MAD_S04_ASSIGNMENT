package com.chichi289.week3.presentation.user_detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chichi289.week3.data.model.User
import com.chichi289.week3.domain.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.deleteUser(user)
        }
    }

}