package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Home(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp) // to create space between the two images
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(1f)  // the weight for the box
            ) {
                Image(
                    painter = painterResource(id = R.drawable.little_lemon_logo),
                    contentDescription = "Logo Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.width(200.dp)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.profile_pic),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .width(50.dp)
                    .clickable { navController.navigate(Profile.route) },
            )
        }
    }
}
