package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun Home(navController: NavController, database: AppDatabase) {

    var searchPhrase by remember {
        mutableStateOf("")
    }

    val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())

    var menuItems = if (searchPhrase.isNotEmpty()) {
        databaseMenuItems.filter {
            it.title.contains(searchPhrase, ignoreCase = true)
        }
    } else {
        databaseMenuItems
    }

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
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.green))
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Little Lemon",
                    color = colorResource(id = R.color.yellow),
                    fontSize = 40.sp,
                    letterSpacing = 1.5.sp
                )
                Row {
                    Column {
                        Text(
                            text = "Chicago",
                            color = colorResource(id = R.color.light_grey),
                            fontSize = 24.sp
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                            color = colorResource(id = R.color.light_grey),
                            modifier = Modifier.width(200.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(30.dp))
                    Image(
                        painter = painterResource(id = R.drawable.hero_image),
                        contentDescription = "Hero Image",
                        modifier = Modifier
                            .width(200.dp)
                            .aspectRatio(16f / 20f)  // set the aspectRatio to 16:20
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.FillBounds  // fill the bounds of the container
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = searchPhrase,
                    onValueChange = {
                        searchPhrase = it
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon"
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(text = "Enter search phrase") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.yellow),
                        backgroundColor = colorResource(id = R.color.light_grey)
                    ),
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "ORDER FOR DELIVERY!",
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            textAlign = TextAlign.Left
        )
        Spacer(modifier = Modifier.height(30.dp))
        MenuItemsList(items = menuItems)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItemsList(items: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.width(250.dp)) {
                            Text(
                                text = menuItem.title,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = menuItem.description,
                                fontSize = 16.sp,
                            )
                            Spacer(modifier = Modifier.height(7.dp))
                            Text(
                                text = "$${menuItem.price}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        GlideImage(model = menuItem.image, contentDescription = "Menu Dish")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        )
    }
}
