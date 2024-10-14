
package com.example.touristexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.touristexplorer.model.Itinerary
import com.example.touristexplorer.repository.ItineraryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ItineraryViewModel : ViewModel() {

    private val repository = ItineraryRepository()

    private val _itineraries = MutableStateFlow<List<Itinerary>>(emptyList())
    val itineraries: StateFlow<List<Itinerary>> get() = _itineraries

    init {
        fetchItineraries()
    }

    fun fetchItineraries() {
        viewModelScope.launch {
            val fetchedItineraries = repository.getItineraries()
            _itineraries.value = fetchedItineraries
        }
    }
}
