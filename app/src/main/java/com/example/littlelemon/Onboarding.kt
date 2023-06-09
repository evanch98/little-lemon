package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun OnBoarding(navController: NavController) {

    val sharedPreferences =
        LocalContext.current.getSharedPreferences("UserData", Context.MODE_PRIVATE)


    var showSnackBar by remember {
        mutableStateOf(false)
    }

    var snackBarMessage by remember {
        mutableStateOf("")
    }

    var firstName by remember {
        mutableStateOf("")
    }

    var lastName by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = R.drawable.little_lemon_logo),
            contentDescription = "Logo Image",
            modifier = Modifier.width(200.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(30.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(color = colorResource(id = R.color.green))
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Let's get to know you",
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Personal information",
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            textAlign = TextAlign.Left
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            OutlinedTextField(
                value = firstName,
                onValueChange = {
                    firstName = it
                },
                label = { Text(text = "First Name") },
                placeholder = { Text(text = "Bob") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.green),
                    focusedLabelColor = colorResource(id = R.color.green)
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedTextField(
                value = lastName,
                onValueChange = {
                    lastName = it
                },
                label = { Text(text = "Last Name") },
                placeholder = { Text(text = "Doe") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.green),
                    focusedLabelColor = colorResource(id = R.color.green)
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = { Text(text = "Email") },
                placeholder = { Text(text = "bobdoe@email.com") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.green),
                    focusedLabelColor = colorResource(id = R.color.green)
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = { Text(text = "Password") },
                placeholder = { Text(text = "*********") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.green),
                    focusedLabelColor = colorResource(id = R.color.green)
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                showSnackBar = true
                snackBarMessage = if (firstName.isBlank() ||
                    lastName.isBlank() ||
                    email.isBlank() ||
                    password.isBlank()
                ) {
                    "Registration unsuccessful. Please enter all data."
                } else {
                    sharedPreferences.edit().putString("firstName", firstName).apply()
                    sharedPreferences.edit().putString("lastName", lastName).apply()
                    sharedPreferences.edit().putString("email", email).apply()
                    sharedPreferences.edit().putString("password", password).apply()
                    sharedPreferences.edit().putBoolean("loggedIn", true).apply()
                    navController.navigate(Home.route)
                    "Registration successful!"
                }
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.yellow),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        ) {
            Text(text = "Register", fontSize = 18.sp)
        }
        if (showSnackBar) {
            SnackBar(message = snackBarMessage)
            LaunchedEffect(Unit) {
                delay(2000)
                showSnackBar = false
            }
        }
    }
}
