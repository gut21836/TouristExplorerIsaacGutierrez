
package com.example.touristexplorer.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.touristexplorer.model.Place
import com.example.touristexplorer.viewmodel.HomeViewModel

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val places by viewModel.places.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Explorar Lugares") },
                actions = {
                    IconButton(onClick = { navController.navigate("itinerary") }) {
                        Icon(Icons.Default.List, contentDescription = "Itinerario")
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            items(places) { place ->
                PlaceItem(place = place, onClick = {
                    navController.navigate("details/${place.id}")
                })
                Divider()
            }
        }
    }
}

@Composable
fun PlaceItem(place: Place, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(place.imageUrl),
            contentDescription = place.name,
            modifier = Modifier
                .size(64.dp)
                .padding(end = 8.dp)
        )
        Column {
            Text(text = place.name, style = MaterialTheme.typography.h6)
            Text(text = place.address, style = MaterialTheme.typography.body2)
        }
    }
}
