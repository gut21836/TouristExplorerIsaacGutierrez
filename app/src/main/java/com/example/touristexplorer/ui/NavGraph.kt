
package com.example.touristexplorer.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.touristexplorer.ui.details.DetailsScreen
import com.example.touristexplorer.ui.home.HomeScreen
import com.example.touristexplorer.ui.itinerary.ItineraryScreen
import com.example.touristexplorer.ui.login.LoginScreen

@Composable
fun TouristExplorerNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("details/{placeId}") { backStackEntry ->
            val placeId = backStackEntry.arguments?.getString("placeId")
            DetailsScreen(placeId = placeId, navController = navController)
        }
        composable("itinerary") { ItineraryScreen(navController) }
    }
}
