package com.chichi289.week5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.chichi289.week5.domain.LocalRepository
import com.chichi289.week5.navigation.MainGraph
import com.chichi289.week5.navigation.Screen
import com.chichi289.week5.ui.theme.MAD_S04_ASSIGNMENTTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var localRepository: LocalRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MAD_S04_ASSIGNMENTTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                    shadowElevation = 4.dp
                ) {
                    val users by remember {
                        localRepository.getUser()
                    }.collectAsState(emptyList())

                    val navController = rememberNavController()

                    LaunchedEffect(Unit) {
                        navController.graph.setStartDestination(
                            if (users.isEmpty()) {
                                Screen.Login.route
                            } else {
                                Screen.Main.route
                            }
                        )
                    }

                    MainGraph(
                        navController = navController
                    )
                }
            }
        }
    }
}