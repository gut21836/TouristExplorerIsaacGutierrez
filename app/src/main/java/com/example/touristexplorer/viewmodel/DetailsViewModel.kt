
package com.example.touristexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.touristexplorer.model.Place
import com.example.touristexplorer.repository.PlaceRepository
import com.example.touristexplorer.repository.ItineraryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val placeRepository = PlaceRepository()
    private val itineraryRepository = ItineraryRepository()

    private val _place = MutableStateFlow<Place?>(null)
    val place: StateFlow<Place?> get() = _place

    fun fetchPlaceDetails(placeId: String) {
        viewModelScope.launch {
            val fetchedPlace = placeRepository.getPlaceById(placeId)
            _place.value = fetchedPlace
        }
    }

    fun addToItinerary(place: Place) {
        viewModelScope.launch {
            itineraryRepository.addItinerary(place)
        }
    }
}
