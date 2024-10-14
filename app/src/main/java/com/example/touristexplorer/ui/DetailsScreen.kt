
package com.example.touristexplorer.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.touristexplorer.viewmodel.DetailsViewModel

@Composable
fun DetailsScreen(placeId: String?, navController: NavHostController, viewModel: DetailsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    placeId?.let {
        viewModel.fetchPlaceDetails(it)
    }

    val place by viewModel.place.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(place?.name ?: "Detalles") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) {
        place?.let { place ->
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Image(
                    painter = rememberImagePainter(place.imageUrl),
                    contentDescription = place.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = place.name, style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = place.description, style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { viewModel.addToItinerary(place) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Agregar al Itinerario")
                }
            }
        } ?: run {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}
