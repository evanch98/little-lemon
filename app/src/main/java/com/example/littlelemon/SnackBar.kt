package com.example.littlelemon

import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun SnackBar(
    message: String,
    duration: SnackbarDuration = SnackbarDuration.Short,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(snackbarHostState) {
        snackbarHostState.showSnackbar(message, duration = duration)
    }
    SnackbarHost(hostState = snackbarHostState)
}
