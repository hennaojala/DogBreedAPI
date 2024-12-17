// Import the DogBreed data model.
// This allows the API service to return data of type `DogBreed`.
import rabbitwhite.android.mockydogbreedapi.data.model.DogBreed

// Import the Retrofit `@GET` annotation.
// Retrofit is a library used for making HTTP requests in Android apps.
import retrofit2.http.GET

// Define the `DogApiService` interface.
// Interfaces in Kotlin can declare abstract functions without implementation.
// Retrofit will generate the implementation for this interface automatically.
interface DogApiService {

    // The `@GET` annotation specifies an HTTP GET request.
    @GET("c79426d7-58df-42ee-ac6b-d1f2ca87fddc") // Replace with your actual API endpoint.

    // `suspend` indicates that this function is a coroutine and should be called asynchronously.
    // This helps avoid blocking the main thread when making network calls.
    // The function returns a `List<DogBreed>`, meaning it fetches a list of `DogBreed` objects.
    suspend fun getDogBreeds(): List<DogBreed>
}
