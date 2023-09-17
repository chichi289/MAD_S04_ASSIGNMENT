package com.chichi289.week4.presentation.user_profile

import androidx.lifecycle.ViewModel
import com.chichi289.week4.domain.RandomDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val randomDataRepository: RandomDataRepository
) : ViewModel() {




}