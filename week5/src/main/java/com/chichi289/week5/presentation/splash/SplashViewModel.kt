package com.chichi289.week5.presentation.splash

import androidx.lifecycle.ViewModel
import com.chichi289.week5.domain.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    localRepository: LocalRepository
) : ViewModel() {

    val userFlow = localRepository.getUser()

}