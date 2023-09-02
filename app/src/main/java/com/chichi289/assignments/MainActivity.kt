package com.chichi289.assignments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.chichi289.assignments.data.UserDataSource
import com.chichi289.assignments.domain.UserRepository
import com.chichi289.assignments.presentation.screens.MainViewModel
import com.chichi289.assignments.presentation.screens.UserListScreen
import com.chichi289.assignments.ui.theme.Assignment1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private val userDataSource: UserRepository by lazy {
        UserDataSource()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //MainScreen(mainViewModel)
                    UserListScreen(userRepository = userDataSource)
                }
            }
        }
    }
}