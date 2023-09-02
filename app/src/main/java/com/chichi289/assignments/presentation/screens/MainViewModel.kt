package com.chichi289.assignments.presentation.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    var mClickCount by mutableStateOf(0)

    fun incrementCount() {
        mClickCount++
    }

    fun resetCount() {
        mClickCount = 0
    }

}