package com.example.littlelemon

interface Destination {
    val route: String
}

object OnBoarding: Destination {
    override val route: String = "OnBoarding"
}

object Home: Destination {
    override val route: String = "Home"
}

object Profile: Destination {
    override val route: String = "Profile"
}
