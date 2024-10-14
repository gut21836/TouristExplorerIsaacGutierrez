
package com.example.touristexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.touristexplorer.model.Place
import com.example.touristexplorer.repository.PlaceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = PlaceRepository()

    private val _places = MutableStateFlow<List<Place>>(emptyList())
    val places: StateFlow<List<Place>> get() = _places

    init {
        fetchPlaces()
    }

    fun fetchPlaces() {
        viewModelScope.launch {
            val fetchedPlaces = repository.getNearbyPlaces()
            _places.value = fetchedPlaces
        }
    }
}
