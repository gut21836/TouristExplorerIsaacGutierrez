
package com.example.touristexplorer.ui.itinerary

import androidx.compose.foundation.Image
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
import com.example.touristexplorer.viewmodel.ItineraryViewModel

@Composable
fun ItineraryScreen(navController: NavHostController, viewModel: ItineraryViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val itineraries by viewModel.itineraries.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Itinerario") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) {
        if (itineraries.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No tienes lugares en tu itinerario.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                items(itineraries) { itinerary ->
                    ItineraryItem(itinerary = itinerary)
                    Divider()
                }
            }
        }
    }
}

@Composable
fun ItineraryItem(itinerary: com.example.touristexplorer.model.Itinerary) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(itinerary.imageUrl),
            contentDescription = itinerary.placeName,
            modifier = Modifier
                .size(64.dp)
                .padding(end = 8.dp)
        )
        Column {
            Text(text = itinerary.placeName, style = MaterialTheme.typography.h6)
            Text(text = "Fecha: ${itinerary.date}", style = MaterialTheme.typography.body2)
        }
    }
}
