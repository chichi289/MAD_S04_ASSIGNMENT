package com.chichi289.week4.presentation.user_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chichi289.week4.domain.RandomDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val randomDataRepository: RandomDataRepository
) : ViewModel() {


    private val _mutableErrorStateFlow: MutableStateFlow<String?> = MutableStateFlow(null)
    val errorStateFlow: StateFlow<String?> = _mutableErrorStateFlow

    init {
        viewModelScope.launch {
            randomDataRepository.getUserData(1).collect {

            }
        }
    }

}