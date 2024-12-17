// Package declaration for organizing the ViewModel within the project structure.
package rabbitwhite.android.mockydogbreedapi.viewmodel

// Import necessary classes for ViewModel and coroutine handling.
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope  // Scope tied to the ViewModel's lifecycle.
import kotlinx.coroutines.flow.MutableStateFlow  // Mutable state flow for holding and updating data.
import kotlinx.coroutines.flow.StateFlow          // Read-only state flow for exposing data.
import kotlinx.coroutines.launch                  // For launching coroutines within the ViewModel scope.
import rabbitwhite.android.mockydogbreedapi.data.model.DogBreed  // Data model for dog breeds.
import rabbitwhite.android.mockydogbreedapi.data.network.RetrofitInstance  // Retrofit instance for network calls.

// Define the DogBreedViewModel class, which extends the ViewModel class.
class DogBreedViewModel : ViewModel() {

    // Mutable state flow to hold the list of dog breeds.
    // This can be updated internally within the ViewModel.
    private val _dogBreeds = MutableStateFlow<List<DogBreed>>(emptyList())

    // Read-only state flow to expose the dog breeds to the UI.
    // This ensures encapsulation: the UI cannot modify the data directly.
    val dogBreeds: StateFlow<List<DogBreed>> = _dogBreeds

    // The init block is called when the ViewModel is first created.
    // Automatically fetch the dog breeds when the ViewModel is initialized.
    init {
        fetchDogBreeds()
    }

    // Function to fetch dog breeds from the API.
    private fun fetchDogBreeds() {
        // Launch a coroutine within the ViewModel's scope to perform the network request.
        viewModelScope.launch {
            try {
                // Make the network call to fetch the list of dog breeds.
                val response = RetrofitInstance.api.getDogBreeds()

                // Update the mutable state flow with the fetched data.
                _dogBreeds.value = response
            } catch (e: Exception) {
                // Print the exception stack trace if an error occurs during the network call.
                e.printStackTrace()
            }
        }
    }
}
