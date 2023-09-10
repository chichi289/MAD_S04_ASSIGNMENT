package com.chichi289.week3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.chichi289.week3.domain.LocalRepository
import com.chichi289.week3.navigation.MainGraph
import com.chichi289.week3.navigation.Screen
import com.chichi289.week3.ui.theme.MAD_S04_ASSIGNMENTTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var localRepository: LocalRepository

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MAD_S04_ASSIGNMENTTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(title = {
                                Text(text = stringResource(R.string.txt_user_directory))
                            })
                        }
                    ) { paddingValues ->
                        MainGraph(
                            modifier = Modifier.padding(paddingValues),
                            startDestination = getStartDestination(),
                            navController = rememberNavController()
                        )
                    }
                }
            }
        }
    }

    private fun getStartDestination(): String {
        val b = runBlocking { localRepository.usersAddedInDb.first() }
        return if (b) Screen.UserListScreen.route
        else Screen.WelcomeScreen.route
    }

}