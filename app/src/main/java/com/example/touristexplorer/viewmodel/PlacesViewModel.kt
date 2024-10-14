package com.example.touristexplorer.viewmodel

class PlacesViewModel : ViewModel() {
    private val _places = MutableLiveData<List<Place>>()
    val places: LiveData<List<Place>> get() = _places

    // Función para buscar lugares turísticos cercanos
    fun fetchPlaces(location: LatLng) {
        // Aquí irá la lógica para llamar a la API de Google Places
    }
}
