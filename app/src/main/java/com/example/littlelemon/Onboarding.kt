package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun OnBoarding() {
    Image(
        painter = painterResource(id = R.drawable.little_lemon_logo),
        contentDescription = "Logo Image"
    )
}

@Preview(showBackground = true)
@Composable
fun OnBoardingPreview() {
    OnBoarding()
}
