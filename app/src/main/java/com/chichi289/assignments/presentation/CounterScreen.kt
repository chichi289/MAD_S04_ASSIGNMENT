package com.chichi289.assignments.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.chichi289.assignments.R

/**
 * Composable function that represents the main screen of the application.
 *
 * @param viewModel The [CounterViewModel] used to manage the click count state.
 */

@Composable
fun CounterScreen(viewModel: CounterViewModel = hiltViewModel()) {

    // Get the current lifecycle owner
    val lifecycleOwner = LocalLifecycleOwner.current

    // Set up a DisposableEffect to observe the lifecycle events
    DisposableEffect(lifecycleOwner) {
        val lifecycle = lifecycleOwner.lifecycle

        // Create a lifecycle observer that listens for the ON_PAUSE event
        val lifecycleObserver =
            LifecycleEventObserver { _: LifecycleOwner, event: Lifecycle.Event ->
                if (event == Lifecycle.Event.ON_PAUSE) {
                    // When the app goes to the background (ON_PAUSE), reset the click count
                    viewModel.resetCount()
                }
            }
        // Add the observer to the lifecycle
        lifecycle.addObserver(lifecycleObserver)

        // Remove the observer when the effect is disposed (component is removed from the composition)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Click count ${viewModel.mClickCount}")
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.incrementCount()
            }, colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(text = stringResource(R.string.txt_click_me))
        }
    }
}