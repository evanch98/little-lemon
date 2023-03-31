package com.example.littlelemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// The classes contain data classes that are used to decode the object received from the server
@Serializable
data class MenuNetwork(
    // The serial name represents the main list in the json file
    @SerialName("menu")
    val menu: List<MenuItemNetwork>,
)

@Serializable
data class MenuItemNetwork(
    // Each serial name represents the key included in the json file
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: Double,
    @SerialName("image")
    val image: String,
    @SerialName("category")
    val category: String
)
