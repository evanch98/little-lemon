package com.example.littlelemon

interface Destination {
    val route: String
}

object Home: Destination {
    override val route: String = "Home"
}

object Project: Destination {
    override val route: String = "Profile"
}
